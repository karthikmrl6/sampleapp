package com.aws.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.aws.jobs.Ec2InstanceStartJob;
import com.aws.jobs.Ec2InstanceStopJob;
import com.aws.utilities.EC2InstanceUtilities;
import com.aws.utilities.LoggerUtilities;

/**
 * The Class Main.
 */
public class Main {

    /**
     * The main method.
     * 
     * @param args the arguments
     * @throws SchedulerException the scheduler exception
     */
    public static void main(String[] args) throws SchedulerException {
        Main main = new Main();
        EC2InstanceUtilities ec2InstanceUtilities = new EC2InstanceUtilities();
        Properties prop = new Properties();
        InputStream input = null;

        /**
         * Creating bucket for all logs
         */
        LoggerUtilities loggerUtilities = new LoggerUtilities();
        loggerUtilities.createBucket();

        try {
            // Read Prop file
            input = new FileInputStream("Employees.properties");

            // load a property file
            prop.load(input);
            String startTime = prop.getProperty("startTime");
            String endTime = prop.getProperty("endTime");
            String nameString = prop.getProperty("names");

            String[] name = nameString.split(",");
            List<String> names = new ArrayList<>();
            int size = name.length;

            List<String> instancesNames = ec2InstanceUtilities.getinstancesNames();

            int nameIndex = 1;
            while (nameIndex <= size) {
                String username = name[(nameIndex - 1)];
                names.add(username);
                System.out.println(username);
                if (!instancesNames.contains(username)) {
                    /**
                     * create folder
                     */
                    loggerUtilities.createFolder(username);
                    main.createLogGeneratorScheduler(username);
                    // create Ec2 Instance per User.
                    String instanceID = ec2InstanceUtilities.createEC2InstanceWithTags(username);
                    // Ready the schedulers for each user.
                    main.createStartInstanceJob(instanceID, startTime, username);
                    main.createEndInstanceJob(instanceID, endTime, username);

                } else {
                    System.out.println("Skipped");
                }
                nameIndex++;

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Creates the log generator scheduler.
     * 
     * @param username the username
     * @throws SchedulerException the scheduler exception
     */
    public void createLogGeneratorScheduler(String username) throws SchedulerException {

        JobDetail startJob = JobBuilder.newJob(Ec2InstanceStartJob.class).withIdentity("Creating File", username)
                .build();

        Trigger dailyTrigger = TriggerBuilder.newTrigger().withIdentity("Log creator trigger", username)
                .withSchedule(CronScheduleBuilder.cronSchedule("0 55 7 ? * MON-FRI")).build();

        Scheduler startScheduler = new StdSchedulerFactory().getScheduler();
        startScheduler.start();
        startScheduler.scheduleJob(startJob, dailyTrigger);
    }

    /**
     * Creates the start instance job.
     * 
     * @param instanceID the instance id
     * @param startTime the start time
     * @param username the username
     * @throws SchedulerException the scheduler exception
     */
    public void createStartInstanceJob(String instanceID, String startTime, String username) throws SchedulerException {

        JobDetail startJob = JobBuilder.newJob(Ec2InstanceStartJob.class).withIdentity(instanceID, username).build();

        String cronExpression = new Main().cronMaker(startTime);

        /**
         * Test expression
         * */
        // cronExpression = "0 15/10 11 ? * MON-FRI";
        Trigger dailyTrigger = TriggerBuilder.newTrigger().withIdentity(instanceID, "dailyTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();

        Scheduler startScheduler = new StdSchedulerFactory().getScheduler();
        startScheduler.start();
        startScheduler.scheduleJob(startJob, dailyTrigger);
    }

    /**
     * Creates the end instance job.
     * 
     * @param instanceID the instance id
     * @param endTime the end time
     * @param username the username
     * @throws SchedulerException the scheduler exception
     */
    public void createEndInstanceJob(String instanceID, String endTime, String username) throws SchedulerException {

        JobDetail endJob = JobBuilder.newJob(Ec2InstanceStopJob.class).withIdentity(instanceID, username + "#EndJob")
                .build();

        String cronExpression = new Main().cronMaker(endTime);

        /**
         * Test expression
         * */
        // cronExpression = "0 20/10 11 ? * MON-FRI";
        Trigger dailyTrigger = TriggerBuilder.newTrigger().withIdentity(instanceID, "daily Stopping Trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        Scheduler stopScheduler = new StdSchedulerFactory().getScheduler();
        stopScheduler.start();
        stopScheduler.scheduleJob(endJob, dailyTrigger);
    }

    /**
     * Cron maker.
     * 
     * @param requestedTime the requested time
     * @return the string
     */
    public String cronMaker(String requestedTime) {

        String cronExpression = null;
        String[] timeMeridiem = requestedTime.split(" ");
        String time = timeMeridiem[0];
        String meridiem = timeMeridiem[1];

        String[] minutesHours = time.split(":");
        String hour = minutesHours[0];
        String mint = minutesHours[1];

        if (meridiem.equals("AM") || meridiem.equals("am")) {
            cronExpression = setStartTime(hour, mint);
        } else if (meridiem.equals("PM") || meridiem.equals("pm")) {
            cronExpression = setEndTime(hour, mint);
        } else {
            System.out.println("Time Expression not recognized");
        }
        return cronExpression;
    }

    /**
     * Sets the start time.
     * 
     * @param hour the hour
     * @param mint the mint
     * @return the string
     */
    private String setStartTime(String hour, String mint) {
        String cronExpression = "0 " + mint + " " + hour + " ? * MON-FRI";
        return cronExpression;
    }

    /**
     * Sets the end time.
     * 
     * @param hour the hour
     * @param mint the mint
     * @return the string
     */
    private String setEndTime(String hour, String mint) {
        int hours = 0;
        if (hour.equals("12")) {
            hours = Integer.parseInt(hour);
        } else {
            hours = 12 + Integer.parseInt(hour);
        }

        String cronExpression = "0 " + mint + " " + hours + " ? * MON-FRI";
        return cronExpression;
    }
}

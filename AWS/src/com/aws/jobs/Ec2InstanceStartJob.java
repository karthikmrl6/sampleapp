package com.aws.jobs;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.aws.app.Logger;
import com.aws.utilities.LoggerUtilities;

/**
 * The Class Ec2InstanceStartJob.
 */
public class Ec2InstanceStartJob implements Job {

    /** The credentials provider. */
    public static AWSCredentialsProvider credentialsProvider;

    /** The ec2 client. */
    public static AmazonEC2 ec2Client;

    /*
     * (non-Javadoc)
     * 
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail detail = context.getJobDetail();
        JobKey key = detail.getKey();

        try {
            System.out.println("starting Instance for user: " + key.getGroup());
            startInstance(key.getName(), key.getGroup());
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start instance.
     * 
     * @param instanceId the instance id
     * @param username the username
     * @throws NumberFormatException the number format exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void startInstance(String instanceId, String username) throws NumberFormatException, IOException {

        LoggerUtilities utilities = new LoggerUtilities();
        /**
         * Creating Log files for first time
         */
        try {
            System.out.println("Creating log files");
            utilities.createLogFile(username);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        credentialsProvider = new ClasspathPropertiesFileCredentialsProvider("Gokkulkarthik.properties");
        ec2Client = new AmazonEC2Client(credentialsProvider);
        Logger.info("Starting instance for " + username + "at " + new Date(), username);
        StartInstancesRequest startRequest = new StartInstancesRequest().withInstanceIds(instanceId);
        ec2Client.startInstances(startRequest);
    }
}


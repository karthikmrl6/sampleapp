package com.aws.jobs;

import java.io.IOException;
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
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.aws.app.Logger;

/**
 * The Class Ec2InstanceStopJob.
 */
public class Ec2InstanceStopJob implements Job {

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
        String group = key.getGroup();
        String[] username = group.split("#");
        try {
            stopInstance(key.getName(), username[0]);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Stop instance.
     * 
     * @param instanceId the instance id
     * @param username the username
     * @throws NumberFormatException the number format exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void stopInstance(String instanceId, String username) throws NumberFormatException, IOException {

        credentialsProvider = new ClasspathPropertiesFileCredentialsProvider("Gokkulkarthik.properties");
        ec2Client = new AmazonEC2Client(credentialsProvider);
        Logger.info("Stopping Instance for " + username + "at " + new Date(), username);
        StopInstancesRequest stopRequest = new StopInstancesRequest().withInstanceIds(instanceId);
        ec2Client.stopInstances(stopRequest);
    }
}
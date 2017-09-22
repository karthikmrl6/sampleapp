package com.aws.jobs;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import com.aws.utilities.LoggerUtilities;

/**
 * The Class GenerateLogFiles.
 */
public class GenerateLogFiles implements Job {

    /*
     * (non-Javadoc)
     * 
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail detail = context.getJobDetail();
        JobKey key = detail.getKey();
        String username = key.getGroup();

        LoggerUtilities loggerUtilities = new LoggerUtilities();
        loggerUtilities.createFolder(username);
    }

}

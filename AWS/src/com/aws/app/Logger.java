package com.aws.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

/**
 * The Class Logger.
 */
public class Logger {

    /** The credentials provider. */
    public static AWSCredentialsProvider credentialsProvider = new ClasspathPropertiesFileCredentialsProvider(
            "Gokkulkarthik.properties");

    /** The bucket name. */
    public static String bucketName = "log.files.bucket.test";

    /** The s3 client. */
    public static AmazonS3Client s3Client = new AmazonS3Client(credentialsProvider);

    /**
     * Info.
     * 
     * @param message the message
     * @param username the username
     * @throws NumberFormatException the number format exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void info(String message, String username) throws NumberFormatException, IOException {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String currentDate = dt.format(date);

        String filename = "info_" + currentDate + ".log";
        String key = username + "/" + filename;

        /**
         * Log file
         */
        File logFile = new File(username + "_" + filename);

        /**
         * if file doesn't exists, then create it
         */
        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        S3Object s3object = s3Client.getObject(new GetObjectRequest(bucketName, key));

        /**
         * Reading Uploaded log file
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
        String line;
        FileWriter fw = new FileWriter(logFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        while ((line = reader.readLine()) != null) {
            bw.write(line);
            bw.newLine();
        }

        /**
         * Write new log
         */
        bw.write(message);
        bw.newLine();
        bw.close();

        s3Client.putObject(new PutObjectRequest(bucketName, key, logFile));
    }

    /**
     * Err.
     * 
     * @param message the message
     * @param username the username
     * @throws NumberFormatException the number format exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void err(String message, String username) throws NumberFormatException, IOException {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String currentDate = dt.format(date);

        String filename = "err_" + currentDate + ".log";
        String key = username + "/" + filename;

        /**
         * Log file
         */
        File logFile = new File(username + "_" + filename);

        /**
         * if file doesn't exists, then create it
         */
        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        S3Object s3object = s3Client.getObject(new GetObjectRequest(bucketName, key));

        /**
         * Reading Uploaded log file
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
        String line;
        FileWriter fw = new FileWriter(logFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        while ((line = reader.readLine()) != null) {
            bw.write(line);
            bw.newLine();
        }

        /**
         * Write new log
         */
        bw.write(message);
        bw.newLine();
        bw.close();

        s3Client.putObject(new PutObjectRequest(bucketName, key, logFile));

    }
}


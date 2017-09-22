package com.aws.utilities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * The Class LoggerUtilities.
 */
public class LoggerUtilities {

	/** The credentials provider. */
	public static AWSCredentialsProvider credentialsProvider = new ClasspathPropertiesFileCredentialsProvider(
			"Gokkulkarthik.properties");

	/** The bucket name. */
	public static String bucketName = "log.files.bucket.testing";

	public static void main(String[] args) {

		new LoggerUtilities().createBucket();
	}

	/**
	 * Creates the bucket.
	 */
	public void createBucket() {
		AmazonS3Client s3Client = new AmazonS3Client(credentialsProvider);
		if (!(s3Client.doesBucketExist(bucketName))) {
			s3Client.createBucket(new CreateBucketRequest(bucketName));
		}
		System.out.println("Created");
	}

	/**
	 * Creates the folder.
	 * 
	 * @param username
	 *            the username
	 * @return true, if successful
	 */
	public boolean createFolder(String username) {
		AmazonS3Client s3Client = new AmazonS3Client(credentialsProvider);
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);

		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, username + "/", emptyContent, metadata);

		// send request to S3 to create folder
		s3Client.putObject(putObjectRequest);
		return true;
	}

	/**
	 * Creates the log file.
	 * 
	 * @param username
	 *            the username
	 * @return true, if successful
	 * @throws ParseException
	 *             the parse exception
	 */
	public boolean createLogFile(String username) throws ParseException {
		AmazonS3Client s3Client = new AmazonS3Client(credentialsProvider);
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String currentDate = dt.format(date);
		System.out.println("Formatted Date: " + currentDate);

		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);

		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putInfoLog = new PutObjectRequest(bucketName, username + "/" + "info_" + currentDate + ".log",
				emptyContent, metadata);

		// send request to S3 to create folder
		s3Client.putObject(putInfoLog);
		System.out.println("Info log file created");
		PutObjectRequest putErrorLog = new PutObjectRequest(bucketName, username + "/" + "err_" + currentDate + ".log",
				emptyContent, metadata);

		// send request to S3 to create folder
		s3Client.putObject(putErrorLog);
		System.out.println("Error log file created");
		return true;

	}

}

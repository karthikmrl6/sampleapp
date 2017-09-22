package com.aws.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.Tag;

/**
 * The Class EC2InstanceUtilities.
 */
public class EC2InstanceUtilities {

	/** The credentials provider. */
	public static AWSCredentialsProvider credentialsProvider;

	/** The instance os code. */
	public static String instanceOSCode = "ami-1df0ac78";

	/** The instance type. */
	public static String instanceType = "t2.micro";

	/** The key name. */
	public static String keyName = "hellogok1";

	/** The security group. */
	public static String securityGroup = "Gokkulkarthik5";

	/** The ec2 client. */
	public static AmazonEC2 ec2Client;

	/**
	 * Creates the ec2 instance with tags.
	 * 
	 * @param username
	 *            the username
	 * @return the string
	 */
	public String createEC2InstanceWithTags(String username) {
		System.out.println("Creating EC2  instance with Name: " + username);
		String instanceID = null;
		credentialsProvider = new ClasspathPropertiesFileCredentialsProvider("Gokkulkarthik.properties");
		ec2Client = new AmazonEC2Client(credentialsProvider);

		RunInstancesRequest instancesRequest = new RunInstancesRequest();

		instancesRequest.withImageId(instanceOSCode).withInstanceType(instanceType).withMinCount(1).withMaxCount(1)
				.withKeyName(keyName).withSecurityGroups(securityGroup);

		RunInstancesResult runInstancesResult = ec2Client.runInstances(instancesRequest);

		List<Instance> instances = runInstancesResult.getReservation().getInstances();
		for (Instance instance : instances) {
			instanceID = instance.getInstanceId();
			CreateTagsRequest createTagsRequest = new CreateTagsRequest();
			createTagsRequest.withResources(instance.getInstanceId()) //
					.withTags(new Tag("Name", username));
			ec2Client.createTags(createTagsRequest);
		}

		return instanceID;
	}

	/**
	 * Gets the instance status.
	 * 
	 * @param instanceId
	 *            the instance id
	 * @return the instance status
	 */
	public Integer getInstanceStatus(String instanceId) {
		DescribeInstancesRequest describeInstanceRequest = new DescribeInstancesRequest().withInstanceIds(instanceId);
		DescribeInstancesResult describeInstanceResult = ec2Client.describeInstances(describeInstanceRequest);
		InstanceState state = describeInstanceResult.getReservations().get(0).getInstances().get(0).getState();
		return state.getCode();
	}

	/**
	 * Start instance.
	 * 
	 * @param instanceId
	 *            the instance id
	 */
	public void startInstance(String instanceId) {
		StartInstancesRequest startRequest = new StartInstancesRequest().withInstanceIds(instanceId);
		StartInstancesResult startResult = ec2Client.startInstances(startRequest);
	}

	/**
	 * Stop instance.
	 * 
	 * @param instanceId
	 *            the instance id
	 */
	public void stopInstance(String instanceId) {
		StopInstancesRequest stopRequest = new StopInstancesRequest().withInstanceIds(instanceId);
		ec2Client.stopInstances(stopRequest);
	}

	/**
	 * Reboot instance.
	 * 
	 * @param instanceId
	 *            the instance id
	 */
	public void rebootInstance(String instanceId) {
		RebootInstancesRequest rebootRequest = new RebootInstancesRequest().withInstanceIds(instanceId);
		ec2Client.rebootInstances(rebootRequest);
	}

	/**
	 * Gets the instances names.
	 * 
	 * @return the instances names
	 */
	/**
	 * public List<String> getinstancesNames() { credentialsProvider = new
	 * ClasspathPropertiesFileCredentialsProvider("Gokkulkarthik.properties");
	 * ec2Client = new AmazonEC2Client(credentialsProvider);
	 * DescribeInstancesResult describeInstancesRequest =
	 * ec2Client.describeInstances(); List<Reservation> reservations =
	 * describeInstancesRequest.getReservations(); List<String> instancesNames =
	 * new ArrayList();
	 * 
	 * for (Reservation reservation : reservations) { Instance instance =
	 * reservation.getInstances().iterator().next(); List<Tag> tags =
	 * instance.getTags(); Iterator<Tag> itr = tags.iterator(); while
	 * (itr.hasNext()) { Tag tag = itr.next();
	 * instancesNames.add(tag.getValue()); }
	 * 
	 * } return instancesNames; }
	 */

	public List<String> getinstancesNames() {
		credentialsProvider = new ClasspathPropertiesFileCredentialsProvider("Gokkulkarthik.properties");
		ec2Client = new AmazonEC2Client(credentialsProvider);
		DescribeInstancesResult describeInstancesRequest = ec2Client.describeInstances();
		List<Reservation> reservations = describeInstancesRequest.getReservations();
		List<String> instancesNames = new ArrayList();

		for (Reservation reservation : reservations) {
			Instance instance = reservation.getInstances().iterator().next();
			List<Tag> tags = instance.getTags();
			Iterator<Tag> iterator = tags.iterator();
			while (iterator.hasNext()) {
				Tag tag = iterator.next();
				instancesNames.add(tag.getValue());
			}

		}
		return instancesNames;
	}

}

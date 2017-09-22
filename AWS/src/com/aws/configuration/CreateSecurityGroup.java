package com.aws.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.IpPermission;

/**
 * The Class CreateSecurityGroup.
 */
public class CreateSecurityGroup {

    /** The credentials provider. */
    public static AWSCredentialsProvider credentialsProvider;

    /** The security group name. */
    public static String securityGroupName = "Gokkulkarthik5";

    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(String[] args) {

    	credentialsProvider = new ClasspathPropertiesFileCredentialsProvider("Gokkulkarthik.properties");
        
        AmazonEC2 ec2Client = new AmazonEC2Client(credentialsProvider);
        ec2Client.setRegion(Region.getRegion(Regions.US_EAST_1));

        CreateSecurityGroupRequest securityGroupRequest = new CreateSecurityGroupRequest();
        securityGroupRequest.withGroupName(securityGroupName).withDescription(
                "This one is developed Gokkul and Karthik for testing purposes");

        ec2Client.createSecurityGroup(securityGroupRequest);
        System.out.println("Security Group created");

        // To authorize security group ingress
        IpPermission permission = new IpPermission();
        permission.withIpRanges("111.111.111.111/32", "150.150.150.150/32").withIpProtocol("tcp").withFromPort(22)
                .withToPort(22);

        // Now Authorize Security Group Ingress
        AuthorizeSecurityGroupIngressRequest ingressRequest = new AuthorizeSecurityGroupIngressRequest();
        ingressRequest.withGroupName(securityGroupName).withIpPermissions(permission);

        // Now add the security Group Ingress Request into ec2Client
        ec2Client.authorizeSecurityGroupIngress(ingressRequest);
        System.out.println("Ingress request Added into security Group");
    }
}

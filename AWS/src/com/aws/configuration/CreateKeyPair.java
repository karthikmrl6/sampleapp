package com.aws.configuration;

import java.io.IOException;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.KeyPair;

/**
 * The Class CreateKeyPair.
 */
public class CreateKeyPair {

    /** The credentials provider. */
    public static AWSCredentialsProvider credentialsProvider;
    public static String keyName = "hellogok1";

    /**
     * The main method.
     * 
     * @param args the arguments
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void main(String[] args) throws IOException {

        credentialsProvider = new ClasspathPropertiesFileCredentialsProvider("Gokkulkarthik.properties");

        // Creating EC2 Client First, on which You will Latch your other
        // attributes.
        AmazonEC2 ec2Client = new AmazonEC2Client(credentialsProvider);

        // Creating Key Pairs
        CreateKeyPairRequest keyPairRequest = new CreateKeyPairRequest();
        keyPairRequest.withKeyName(keyName);

        CreateKeyPairResult keyPairResult = ec2Client.createKeyPair(keyPairRequest);

        KeyPair keyPair = new KeyPair();
        keyPair = keyPairResult.getKeyPair();

        /* to obtain the unencrypted PEM-encoded private key */
        String privateKey = keyPair.getKeyMaterial();
        System.out.println("Private Key: " + privateKey);

    }
}

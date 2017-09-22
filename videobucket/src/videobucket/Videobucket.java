package videobucket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;


public class Videobucket {

    public static void main(String[] args)
    {
        // credentials object identifying user for authentication
        // user must have AWSConnector and AmazonS3FullAccess for 
        // this example to work
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAJDSJH7CRLQIICFQQ",
                "/UoS8UAbCppwDUQBEGGlLP/MrKOkYLDydfuL3KGJ");

        // create a client connection based on credentials
        AmazonS3 client = new AmazonS3Client(credentials);

        // list buckets
        for (Bucket bucket : client.listBuckets()) {
            System.out.println(" - " + bucket.getName());
        }
       
         
            String bucketName = "cs9223-int-f2015";
            String region = "us-east-1";
            String accessid = "AKIAILY4IXFV5AL3GN7Q";
            String secretkey = "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";
            
            S3Object s3object = client.getObject(new GetObjectRequest(
                    bucketName,accessid, secretkey));
            System.out.println(s3object.getObjectMetadata().getContentType());
            System.out.println(s3object.getObjectMetadata().getContentLength());

            BufferedReader reader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
            String line;
            try {
				while((line = reader.readLine()) != null) {
				  // can copy the content locally as well
				  // using a buffered writer
				  System.out.println(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						       
    }
          
          
    }



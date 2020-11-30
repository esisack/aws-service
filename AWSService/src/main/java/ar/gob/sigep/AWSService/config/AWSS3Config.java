package ar.gob.sigep.AWSService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AWSS3Config {

	// Access key id will be read from the application.properties
	@Value("${aws.access_key_id}")
	private String accessKeyId;

	// Secret access key will be read from the application.properties file during
	// the application intialization.
	@Value("${aws.secret_access_key}")
	private String secretAccessKey;

	// Region will be read from the application.properties file during the
	// application intialization.
	@Value("${aws.s3.region}")
	private String region;

	// Region will be read from the application.properties file during the
	// application intialization.
	@Value("${aws.s3.end_point}")
	private String endpoint;

	@Bean
	public AmazonS3 getAmazonS3Cient() {
		final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
		// Get AmazonS3 client and return the s3Client object.
		/*
		 * AmazonS3 s3 = AmazonS3ClientBuilder.standard() // .withEndpointConfiguration(
		 * new EndpointConfiguration(endpoint, region)) .withRegion(region)
		 * .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
		 * .build();
		 */

		ClientConfiguration myClientConfig = new ClientConfiguration();
		myClientConfig.setMaxConnections(200);

		myClientConfig.setProtocol(Protocol.HTTPS);
		System.setProperty("com.amazonaws.sdk.disableCertChecking", "true");
		AmazonS3 s3 = new AmazonS3Client(basicAWSCredentials, myClientConfig);

		s3.setEndpoint(endpoint);

		return s3;
	}
}

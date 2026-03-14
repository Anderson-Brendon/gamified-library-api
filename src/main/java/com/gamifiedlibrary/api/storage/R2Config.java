package com.gamifiedlibrary.api.storage;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;


@Configuration
public class R2Config {
	

    @Value("${r2.endpoint}")
    private String endpoint;

    @Value("${r2.access-key}")
    private String accessKey;

    @Value("${r2.secret-key}")
    private String secretKey;

    @Bean
    S3Client getS3Connection() {
    	var credentials = AwsBasicCredentials.create(accessKey, secretKey);
    	
    	var s3ClientInstance = S3Client.builder().endpointOverride(URI.create(endpoint))
        .credentialsProvider(StaticCredentialsProvider.create(credentials))
        .region(Region.of("auto"))
        // necessário para path-style URLs do R2
        .forcePathStyle(true)
        .build();
    	
    	System.out.println("TESTE R2");
    	System.out.println(s3ClientInstance.listBuckets());
    	
    	return s3ClientInstance;
    	
    }

    @Bean
    S3Presigner s3Presigner(){
        var credentials = AwsBasicCredentials.create(accessKey, secretKey);
    	
    	var s3PresignerInstance = S3Presigner.builder().endpointOverride(URI.create(endpoint))
        .credentialsProvider(StaticCredentialsProvider.create(credentials))
        .region(Region.of("auto")).serviceConfiguration(
            S3Configuration.builder()
                .pathStyleAccessEnabled(true)
                .build()
        ).build();
    	
    	return s3PresignerInstance;
    }
    
}

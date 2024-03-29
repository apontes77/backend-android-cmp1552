package com.pucgoias.devmobileapps.trabalhofinal.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfiguration {

    @Value("${amazon.s3.access-key}")
    private String awsId;
    @Value("${amazon.s3.secret-key}")
    private String awsKey;

    @Bean
    public AmazonS3 s3client(){
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, awsKey);

        return AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}

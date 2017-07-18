package com.pp.stpoint.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app.aws")
public class AWSConfig {

    private String accesskey;
    private String secretkey;
    private String endpoint;
    private String bucket;
    
    @Bean
    public AWSCredentials credentials(){
        return new BasicAWSCredentials(
                accesskey,
                secretkey);
    }
    
    @Bean
    public AmazonS3Client s3Client() {
        AmazonS3Client newClient = new AmazonS3Client(credentials(),
                new ClientConfiguration());
        newClient.setS3ClientOptions(new S3ClientOptions()
                .withPathStyleAccess(true));
        newClient.setEndpoint(endpoint);
        return newClient;

    }

    /**
     * @return the accesskey
     */
    public String getAccesskey() {
        return accesskey;
    }

    /**
     * @param accesskey the accesskey to set
     */
    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    /**
     * @return the secretkey
     */
    public String getSecretkey() {
        return secretkey;
    }

    /**
     * @param secretkey the secretkey to set
     */
    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    /**
     * @return the endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param endpoint the endpoint to set
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return the bucket
     */
    public String getBucket() {
        return bucket;
    }

    /**
     * @param bucket the bucket to set
     */
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}

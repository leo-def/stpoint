package com.pp.stpoint.service.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.pp.stpoint.service.FileService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */

@Service
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app.aws")
public class S3FileService  implements FileService{

    @Autowired
    AmazonS3Client s3Client;
    
    final UrlUtils urlUtils = new UrlUtils();
    
    private String accesskey;
    private String secretkey;
    private String endpoint;
    private String bucket;
    
    
    @Override
    public String write(String baseFolder, MultipartFile multipartFile) {
        return write(baseFolder, multipartFile.getOriginalFilename(), multipartFile);
    }
    
    
    
    @Override
    public String write(final String baseFolder,final String fileName, MultipartFile multipartFile) {
    	String uploadKey = urlUtils.getFullName(baseFolder, fileName);	
    	
      
        try { 
            PutObjectRequest request = 
                     new PutObjectRequest(
                             getBucket(),
                             uploadKey,
                             multipartFile.getInputStream(),
                             getObjectMetadata(multipartFile)
                     );
            request.getRequestClientOptions().setReadLimit((int)multipartFile.getSize());//10000000
            s3Client.putObject(request);
            return urlUtils.keyToUrl(uploadKey);
                    
        } catch (AmazonClientException | IOException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    @Override
    public String update(String url, String baseFolder, MultipartFile multipartFile){
        return update(url, baseFolder, multipartFile.getOriginalFilename(), multipartFile);
    }
    
    @Override
    public String update(String url, String baseFolder, String fileName, MultipartFile multipartFile){
        delete(url);
        return write(baseFolder,fileName, multipartFile);
    }
    
    @Override
    public boolean delete(String url){
        if(url == null){return false;}
        s3Client.deleteObject(getBucket(), urlUtils.urlToKey(url));
        return true;
    }
    public ObjectMetadata getObjectMetadata(MultipartFile multipartFile){
        ObjectMetadata metaData =  new ObjectMetadata();
        metaData.setContentLength(multipartFile.getSize());
        metaData.setContentType(multipartFile.getContentType());
        return metaData;
    }
    public File getFile(MultipartFile multipartFile) throws IOException{
        File convFile = new File(multipartFile.getOriginalFilename());
        convFile.createNewFile(); 
        FileOutputStream fos = new FileOutputStream(convFile); 
        fos.write(multipartFile.getBytes());
        fos.close(); 
        return convFile;
    }
    //private
    class UrlUtils{
        
        private String getFullName(String baseFolder, String fileName){
        	LocalDateTime dateTime = LocalDateTime.now();
        	
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss");
            return new StringBuilder()
                .append(baseFolder)
                .append("_")
                .append(dateTime.format(formatter))
                .append("_")
                .append(fileName)
                .toString();
        }
        private String keyToUrl(String key){
            return new StringBuilder()
                .append(getPrefix())
                .append(key)
                .append(getSuffix())
                .toString();
        }
        private String urlToKey(String url){
            if(url == null){return null;}
            int beginIndex = getPrefix().length();
            int endIndex = url.indexOf(getSuffix());
            return url.substring(beginIndex, endIndex);
        }
        private String getPrefix(){
            return new StringBuilder()
                    .append(getEndpoint())
                    .append("/")
                    .append(getBucket())
                    .append("/").toString();
        }
        private String getSuffix(){
            return "?noAuth=true";
        }
        
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
/*InputStream inputStream = null;

try {
	inputStream = multipartFile.getInputStream();
} catch (IOException e) {
	e.printStackTrace();
	return null;
}

PutObjectRequest putObjectRequest = new PutObjectRequest(getBucket(), uploadKey, inputStream, getObjectMetadata(multipartFile));
putObjectRequest.getRequestClientOptions().setReadLimit((int) multipartFile.getSize());
putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

//PutObjectResult putObjectResult = s3Client.putObject(putObjectRequest);

 s3Client.putObject(putObjectRequest);

IOUtils.closeQuietly(inputStream,new Log4JLogger());

return urlUtils.keyToUrl(uploadKey);
*/
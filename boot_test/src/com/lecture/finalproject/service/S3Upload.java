package com.lecture.finalproject.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
 
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
 
public class S3Upload {
     
    private String accessKey = "AKIAJN2OOMMG6LLSG5WA";
    private String secretKey = "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";
     
    private AmazonS3 conn;
     
    public S3Upload() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        this.conn = new AmazonS3Client(credentials, clientConfig);
        conn.setEndpoint("s3-ap-northeast-1.amazonaws.com");
    }
     
    // Bucket List
   
     
    // ���� ���� (������ ���ϸ� �ڿ� "/"�� �ٿ����Ѵ�.)
    public void createFolder(String bucketName, String folderName) {
        conn.putObject(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
    }
     
    // ���� ���ε�
    public void fileUpload(String bucketName, File file) throws FileNotFoundException {
        conn.putObject(bucketName, file.getName(), new FileInputStream(file), new ObjectMetadata());
    }
     
    // ���� ����
    public void fileDelete(String bucketName, String fileName) {
        conn.deleteObject(bucketName, fileName);
    }
     
    // ���� URL
    public String getFileURL(String bucketName, String fileName) {
        return conn.generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, fileName)).toString();
    }
     
}


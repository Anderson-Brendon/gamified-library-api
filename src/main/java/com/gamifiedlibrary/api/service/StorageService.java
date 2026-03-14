package com.gamifiedlibrary.api.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

//usa objeto do s3client para interagir com r2

@Service
public class StorageService {

    public StorageService(S3Client s3Client, S3Presigner s3Presigner){
        this.s3Client = s3Client;
        this.s3Presigner = s3Presigner;
    }

    S3Client s3Client;

    S3Presigner s3Presigner;

    @Value("${r2.bucket}")
    private String bucketName;

    //cria url que permite o usuário que tiver um token verificado enviar imagem para alterar perfil
    public String createPresignedUrlToPut(String prefix, String keyName){
            
            //construtor do objeto(vulgo arquivo)
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(prefix + keyName)
                    .build();
            
            //construtor do solicitacao presigned
            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(3))  // a url expira em 3 minutos
                    .putObjectRequest(objectRequest)
                    .build();

            //criacao a da url presigned
            PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);
           
            return presignedRequest.url().toExternalForm();
    
}

}

/*key é a chave/nome pra acessar um object, usarei o proprio nome de perfil do usuario */
/*
 String myURL = presignedRequest.url().toString();


            System.out.println("Presigned URL to upload a file to: [{}]" + myURL);
            System.out.println("HTTP method: [{}] " + presignedRequest.httpRequest().method());

*/
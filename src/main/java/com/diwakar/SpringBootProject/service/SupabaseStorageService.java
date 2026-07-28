package com.diwakar.SpringBootProject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class SupabaseStorageService {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.bucket}")
    private String bucket;

    @Value("${supabase.service-key}")
    private String serviceKey;

    public String uploadImage(MultipartFile file) throws IOException {

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        String uploadUrl =
                supabaseUrl +
                        "/storage/v1/object/" +
                        bucket +
                        "/" +
                        fileName;

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(serviceKey);

        headers.setContentType(
                MediaType.parseMediaType(
                        file.getContentType()
                )
        );

        HttpEntity<byte[]> entity =
                new HttpEntity<>(
                        file.getBytes(),
                        headers
                );

        RestTemplate restTemplate =
                new RestTemplate();

        ResponseEntity<String> response =
                restTemplate.exchange(
                        uploadUrl,
                        HttpMethod.POST,
                        entity,
                        String.class
                );

        if(response.getStatusCode().is2xxSuccessful()){

            return supabaseUrl
                    + "/storage/v1/object/public/"
                    + bucket
                    + "/"
                    + fileName;
        }

        throw new RuntimeException("Image Upload Failed");
    }

}
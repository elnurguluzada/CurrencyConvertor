package com.example.CurrencyConvertor.repository.repositoryImpl;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Repository
public class CurrencyRepositoryImpl {



   private RestTemplate restTemplate = new RestTemplate();
   private HttpHeaders headers = new HttpHeaders();


   private void something(){
       headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
       headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
       HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);



   }







}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.givemetoken;

import com.example.givemetoken.mapper.MybatisMapper;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author root
 */
@RestController
public class GiveMeTokenController {
    
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    RestTemplate restTemplate = new RestTemplate();
    //@Autowired
    //MybatisMapper mm;
    
    @RequestMapping("/givemetoken")
    public JSONObject getToken(@RequestParam String code, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("code", code);
        params.add("client_id", "test");
        params.add("client_secret", "abcd");
        params.add("redirect_uri", "http://localhost:9998/givemetoken");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9898/oauth/token", requestEntity, String.class);
        String token = response.getBody();
        
        JSONObject tj = JSONObject.parseObject(token);
        //mm.insertToken(tj.getString("access_token"), tj.getString("refresh_token"), code, request.getRemoteAddr(), tj.getString("expires_in"), df.format(new Date(System.currentTimeMillis())));
        
        return tj;
    }
    
}

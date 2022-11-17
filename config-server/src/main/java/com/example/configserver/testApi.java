package com.example.configserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/git")
public class testApi {

    @Value("${spring.cloud.config.server.git.uri}")
    private String uriVal;

    @GetMapping("/get-uri")
    public String getUri() {
        return uriVal;
    }
}

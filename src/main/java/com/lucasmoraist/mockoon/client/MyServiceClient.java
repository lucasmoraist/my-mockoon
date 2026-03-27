package com.lucasmoraist.mockoon.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(name = "my-service", url = "http://localhost:8080/v1/time-to-live")
public interface MyServiceClient {

    @PostMapping("/callback/{id}")
    Response callback(@PathVariable UUID id);

}

package com.lucasmoraist.mockoon.infrastructure.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(name = "time-to-live", url = "http://localhost:8080/v1/time-to-live")
public interface TimeToLiveClient {

    @PostMapping("/callback/{id}")
    Response callback(@PathVariable UUID id);

}

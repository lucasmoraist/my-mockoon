package com.lucasmoraist.mockoon.infrastructure.api.web.controller;

import com.lucasmoraist.mockoon.domain.model.MyData;
import com.lucasmoraist.mockoon.application.usecases.time_to_live.ExecuterCallback;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExternalServiceController {

    private final ExecuterCallback executerCallback;

    @PostMapping("/process")
    public ResponseEntity<Void> processData(@RequestBody MyData data) {
        this.executerCallback.processData(data);
        return ResponseEntity.noContent().build();
    }

}

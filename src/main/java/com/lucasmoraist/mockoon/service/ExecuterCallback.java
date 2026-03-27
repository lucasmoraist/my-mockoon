package com.lucasmoraist.mockoon.service;

import com.lucasmoraist.mockoon.client.MyServiceClient;
import com.lucasmoraist.mockoon.model.MyData;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ExecuterCallback {

    private final MyServiceClient myServiceClient;

    public ExecuterCallback(MyServiceClient myServiceClient) {
        this.myServiceClient = myServiceClient;
    }

    @Async
    public void processData(MyData data) {
        log.info("Processando dados: {}", data);
        UUID transactionId = UUID.fromString((String) data.metadata().get("transactionId"));

        try {
            Thread.sleep(3000); // Simula um processamento demorado
            Response response = this.myServiceClient.callback(transactionId);

            if (response.status() == 204) {
                log.info("[{}] - Callback enviado com sucesso", transactionId);
            } else {
                log.warn("[{}] - Callback falhou com status {}", transactionId, response.status());
            }
        } catch (InterruptedException e) {
            log.error("[{}] - Processamento interrompido", transactionId, e);
            Thread.currentThread().interrupt();
        }
    }

}

package com.lucasmoraist.mockoon.application.usecases.time_to_live;

import com.lucasmoraist.mockoon.domain.model.MyData;
import com.lucasmoraist.mockoon.infrastructure.client.TimeToLiveClient;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ExecuterCallback {

    private final TimeToLiveClient timeToLiveClient;

    public ExecuterCallback(TimeToLiveClient timeToLiveClient) {
        this.timeToLiveClient = timeToLiveClient;
    }

    @Async
    public void processData(MyData data) {
        log.info("Processando dados: {}", data);
        UUID transactionId = UUID.fromString((String) data.metadata().get("transactionId"));

        try {
            Thread.sleep(3000); // Simula um processamento demorado

            try (Response response = this.timeToLiveClient.callback(transactionId)) {

                if (response.status() < 300) {
                    log.info("[{}] - Callback enviado com sucesso", transactionId);
                } else {
                    log.warn("[{}] - Callback falhou com status {}", transactionId, response.status());
                }
            }
        } catch (InterruptedException e) {
            log.error("[{}] - Processamento interrompido", transactionId, e);
            Thread.currentThread().interrupt();
        }
    }

}

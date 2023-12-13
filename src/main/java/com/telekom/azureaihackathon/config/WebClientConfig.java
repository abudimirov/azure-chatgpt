package com.telekom.azureaihackathon.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    @Value("${azure.url}")
    private String azureUrl;

    @Value("${azure.openApiKey}")
    private String azureOpenApiKey;

    @Bean(name = "gptWebClient")
    public WebClient internalWebclient() {
        return WebClient.builder()
            .baseUrl(azureUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("api-key", azureOpenApiKey)
            .build();
    }
}

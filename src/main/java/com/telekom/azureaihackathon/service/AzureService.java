package com.telekom.azureaihackathon.service;

import com.telekom.azureaihackathon.model.GptResponse;
import com.telekom.azureaihackathon.model.Message;
import com.telekom.azureaihackathon.model.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AzureService {

    @Qualifier("gptWebClient")
    private final WebClient gptWebClient;

    public String getSimpleAnswerChatGpt(final String query) {
        final var request = new Query(Collections.singletonList(new Message("user", query)));
        final var response = gptWebClient
            .post()
            .bodyValue(request)
            .retrieve()
            .bodyToMono(GptResponse.class)
            .block();
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            throw new RuntimeException("Not correct response from ChatGPT");
        }
        return response.getChoices().get(0).getMessage().getContent();
    }
}

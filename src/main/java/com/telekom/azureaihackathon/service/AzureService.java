package com.telekom.azureaihackathon.service;

import com.telekom.azureaihackathon.model.GptResponse;
import com.telekom.azureaihackathon.model.Message;
import com.telekom.azureaihackathon.model.StaticVariables;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AzureService {

    @Qualifier("gptWebClient")
    private final WebClient gptWebClient;

    public String getSimpleAnswerChatGpt(final String query) {
        if (StaticVariables.context.getMessages().isEmpty())
            StaticVariables.context.getMessages().add(new Message("system", "You are a helpful assistant."));
        StaticVariables.context.getMessages().add(new Message("user", query));
        StaticVariables.context.getMessages().add(new Message("user", query));
        final var response = gptWebClient
            .post()
            .bodyValue(StaticVariables.context)
            .retrieve()
            .bodyToMono(GptResponse.class)
            .block();
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            throw new RuntimeException("Not correct response from ChatGPT");
        }
        var previousResponse = response.getChoices().get(0).getMessage();
        StaticVariables.context.getMessages().add(previousResponse);
        return previousResponse.getContent();
    }
}

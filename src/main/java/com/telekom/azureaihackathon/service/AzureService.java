package com.telekom.azureaihackathon.service;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import com.azure.core.credential.AzureKeyCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AzureService {

    @Value("${azure.url}")
    private String azureUrl;

    @Value("${azure.openApiKey}")
    private String azureOpenApiKey;

    @Value("${azure.chatGptVersion}")
    private String chatGptVersion;

    public List<String> getAnswerChatGpt(final String query) {
        OpenAIClient client = new OpenAIClientBuilder()
            .endpoint(azureUrl)
            .credential(new AzureKeyCredential(azureOpenApiKey))
            .buildClient();

        List<ChatMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatMessage(ChatRole.SYSTEM, "You are a helpful assistant"));
        chatMessages.add(new ChatMessage(ChatRole.USER, query));

        ChatCompletions chatCompletions = client.getChatCompletions(chatGptVersion,
            new ChatCompletionsOptions(chatMessages));

        List<String> response = new ArrayList<>();

        chatCompletions.getChoices()
            .forEach(chatChoice -> response.add(chatChoice.getMessage().toString()));

        return response;
    }
}

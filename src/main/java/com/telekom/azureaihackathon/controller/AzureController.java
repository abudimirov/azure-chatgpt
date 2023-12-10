package com.telekom.azureaihackathon.controller;

import com.telekom.azureaihackathon.service.AzureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AzureController {

    private final AzureService azureService;

    @PostMapping(value = "/{query}")
    public ResponseEntity<List<String>> askChatGpt(@PathVariable String query) {
        return ResponseEntity.ok().body(azureService.getAnswerChatGpt(query));
    }
}

package com.telekom.azureaihackathon.controller;

import com.telekom.azureaihackathon.service.AzureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class AzureController {

    public AzureController(AzureService azureService) {
        this.azureService = azureService;
    }

    private final AzureService azureService;

    @PostMapping
    public ResponseEntity<String> askChatGpt(@RequestBody String query) {
        return ResponseEntity.ok().body(azureService.getSimpleAnswerChatGpt(query));
    }
}

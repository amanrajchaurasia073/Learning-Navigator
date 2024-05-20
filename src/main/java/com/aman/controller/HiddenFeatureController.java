package com.aman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HiddenFeatureController {

    private final String NUMBERS_API_URL = "http://numbersapi.com/";
    private final RestTemplate restTemplate;
    @Autowired
    public HiddenFeatureController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hidden-feature/{number}")
    public ResponseEntity<String> getNumberFact(@PathVariable String number) {
        String apiUrl = NUMBERS_API_URL + number;
        String fact = restTemplate.getForObject(apiUrl, String.class);
        return ResponseEntity.ok(fact);
    }
}
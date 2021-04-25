package dev40.querydls.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardArticleController {
    @GetMapping("/")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello, world!");
    }
}

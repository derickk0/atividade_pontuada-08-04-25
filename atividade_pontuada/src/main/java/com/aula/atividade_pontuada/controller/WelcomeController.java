package com.aula.atividade_pontuada.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("API de funcionários, Bem-Vindo!");
    }

    @GetMapping("/desenvolvedor")
    public ResponseEntity<String> desenvolvedor() {
        return ResponseEntity.ok("Nome do desenvolvedor: Erick Breno Santana Pereira");
    }
}

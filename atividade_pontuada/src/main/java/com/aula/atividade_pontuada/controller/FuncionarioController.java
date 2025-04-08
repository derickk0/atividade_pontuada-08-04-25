package com.aula.atividade_pontuada.controller;

import com.aula.atividade_pontuada.model.Funcionario;
import com.aula.atividade_pontuada.repository.FuncionarioRepository;
import com.aula.atividade_pontuada.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin(origins = "*")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService service;

    @PostMapping
    public ResponseEntity<?> salvarFuncionario(@RequestBody Funcionario funcionario) {
        try {
            service.salvarFuncionario(funcionario);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Funcionário cadastrado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não foi possível realizar o cadastro: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarPorId(@PathVariable Long id) {
        try {
            Funcionario funcionario = service.encontrarPorId(id);
            return ResponseEntity.ok(funcionario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O funcionário especificado não foi encontrado: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarFuncionario() {
        return ResponseEntity.ok(service.listarFuncionario());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) {
        try {
            Funcionario atualizado = service.atualizarFuncionario(id, funcionario);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não foi possível atualizar o funcionário: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarFuncionario(@PathVariable Long id) {
        try {
            Funcionario funcionario = service.encontrarPorId(id);
            service.deletarFuncionario(id);
            return ResponseEntity.ok("Funcionário deletado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível deletar o funcionário: " + e.getMessage());
        }
    }
}

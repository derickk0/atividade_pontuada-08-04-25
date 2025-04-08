package com.aula.atividade_pontuada.service;

import com.aula.atividade_pontuada.model.Funcionario;
import com.aula.atividade_pontuada.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public Funcionario salvarFuncionario(Funcionario funcionarioSalvar) {
        if (funcionarioRepository.existsByEmail(funcionarioSalvar.getEmail())){
            throw new IllegalArgumentException("Email já cadastrado");
        }
        return funcionarioRepository.save(funcionarioSalvar);
    }

    public Funcionario encontrarPorId(Long id){
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));
    }

    @Transactional
    public Funcionario atualizarFuncionario(Long id, Funcionario funcionarioAtualizar){
        Funcionario funcionario1 = encontrarPorId(id);

        if (!funcionario1.getEmail().equals(funcionario1.getEmail()) && funcionarioRepository.existsByEmail(funcionarioAtualizar.getEmail())) {
            throw new IllegalArgumentException("Este email já está cadastrado");
        }
        funcionarioAtualizar.setId(funcionario1.getId());
        return funcionarioRepository.save(funcionarioAtualizar);
    }

    public List<Funcionario> listarFuncionario() {
        return funcionarioRepository.findAll();
    }

    @Transactional
    public void deletarFuncionario(Long id) {
        Funcionario funcionario = encontrarPorId(id);
        funcionarioRepository.delete(funcionario);
    }
}

package com.senac.pi.service;

import com.senac.pi.data.FuncionarioEntity;
import com.senac.pi.data.FuncionarioRepository;
import com.senac.pi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public FuncionarioEntity getFuncionarioPorId(Integer funcId) {
        return funcionarioRepository.findById(funcId).orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));
    }
    
}

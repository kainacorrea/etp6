package com.senac.pi.service;

import com.senac.pi.data.FuncionarioEntity;
import com.senac.pi.data.FuncionarioRepository;
import com.senac.pi.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public FuncionarioEntity getFuncionarioPorId(Integer funcId) {
        return funcionarioRepository.findById(funcId).orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));
    }

    public FuncionarioEntity cadastrarFuncionario(FuncionarioEntity f) {
        f.setId(null);
        funcionarioRepository.save(f);
        return f;
    }
    
    public List<FuncionarioEntity> getTodosFuncionarios(){
        return funcionarioRepository.findAll();
    }
    
    public FuncionarioEntity atualizarFilme(Integer id, FuncionarioEntity funcionarioAtualizado) {
        FuncionarioEntity f = getFuncionarioPorId(id);

        f.setNome(funcionarioAtualizado.getNome());
        f.setCpf(funcionarioAtualizado.getCpf());
        f.setEmail(funcionarioAtualizado.getEmail());
        f.setEndereco(funcionarioAtualizado.getEndereco());
        f.setTelefone(funcionarioAtualizado.getTelefone());
        f.setObservacoes(funcionarioAtualizado.getObservacoes());
        
        funcionarioRepository.save(f);
        
        return f;
    }
    
    public boolean deletarFuncionario(Integer id){
        funcionarioRepository.deleteById(getFuncionarioPorId(id).getId());
        return true;
    }
}

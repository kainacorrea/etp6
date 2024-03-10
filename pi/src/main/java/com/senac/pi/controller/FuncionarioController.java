package com.senac.pi.controller;

import com.senac.pi.data.FuncionarioEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FuncionarioController {
    
    // Lista para não ser necessário utilizar banco de dados
    private List<FuncionarioEntity> funcionarios = new ArrayList<>();
    
    @GetMapping("/")
    public String indexPagina (){
        return "index";
    }
    
    @GetMapping("/cadastro")
    public String cadastroFuncionario(Model model){
        model.addAttribute("funcionario", new FuncionarioEntity());
        return "cadastrarView";
    }
    
    @PostMapping("/cadastrar")
    public String processarCadastro(@ModelAttribute FuncionarioEntity f, Model model){
        f.setId(funcionarios.size()+1);
        funcionarios.add(f);
        
        return "redirect:/";
    }
    
    @GetMapping("/listagem")
    public String listagemFuncionarios(Model model){
        model.addAttribute("funcionarios", funcionarios);
        
        return "listarView";
    }
    
    @GetMapping("/detalhes")
    public String mostrarDetalhes(@RequestParam String id, Model model){
        FuncionarioEntity funcEncontrado = new FuncionarioEntity();
        
        for(FuncionarioEntity f : funcionarios){
            if(f.getId() == Integer.parseInt(id)){
                funcEncontrado = f;
                break;
            }
        }
        
        model.addAttribute("funcionario", funcEncontrado);
        return "mostrarView";
    }
    
    @GetMapping("/alterar")
    public String alterarFuncionario(Model model, @RequestParam String id){
        Integer idFunc = Integer.parseInt(id);
        FuncionarioEntity funcEncontrado = new FuncionarioEntity();
        
        for(FuncionarioEntity f : funcionarios){
            if(f.getId() == idFunc){
                funcEncontrado = f;
                break;
            }
        }
        
        model.addAttribute("funcionario", funcEncontrado);
        
        return "cadastrarView";
    }
    
    @PostMapping("/atualizarFuncionario")
    public String salvarFilmeAtualizado(FuncionarioEntity fAtualizado){
        for(FuncionarioEntity f : funcionarios){
            if(f.getId() == fAtualizado.getId()){
                f.setNome(fAtualizado.getNome());
                f.setCpf(fAtualizado.getCpf());
                f.setEmail(fAtualizado.getEmail());
                f.setEndereco(fAtualizado.getEndereco());
                f.setObservacoes(fAtualizado.getObservacoes());
                f.setTelefone(fAtualizado.getTelefone());
            }
        }
        return "redirect:/listagem";
    }
    
    @GetMapping("/deletarFuncionario")
    public String deletarFuncionario(@RequestParam String id){
        for(FuncionarioEntity f : funcionarios){
            if(f.getId() == Integer.parseInt(id)){
                funcionarios.remove(f);
                break;
            }
        }
        
        return "redirect:/listagem";
    }
}

package com.senac.pi.controller;

import com.senac.pi.data.FuncionarioEntity;
import com.senac.pi.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FuncionarioController {
    
    @Autowired
    FuncionarioService funcionarioService;
            
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
        funcionarioService.cadastrarFuncionario(f);
        
        return "redirect:/";
    }
    
    @GetMapping("/listagem")
    public String listagemFuncionarios(Model model){
        model.addAttribute("funcionarios", funcionarioService.getTodosFuncionarios());
        
        return "listarView";
    }
    
    @GetMapping("/detalhes")
    public String mostrarDetalhes(@RequestParam String id, Model model){
        model.addAttribute("funcionario", funcionarioService.getFuncionarioPorId(Integer.parseInt(id)));
        
        return "mostrarView";
    }
    
    @GetMapping("/alterar")
    public String alterarFuncionario(Model model, @RequestParam String id){
        model.addAttribute("funcionario", funcionarioService.getFuncionarioPorId(Integer.parseInt(id)));
        
        return "cadastrarView";
    }
    
    @PostMapping("/atualizarFuncionario")
    public String salvarFilmeAtualizado(FuncionarioEntity fAtualizado){
        funcionarioService.atualizarFilme(fAtualizado.getId(), fAtualizado);
        return "redirect:/listagem";
    }
    
    @GetMapping("/deletarFuncionario")
    public String deletarFuncionario(@RequestParam String id){
        funcionarioService.deletarFuncionario(Integer.parseInt(id));
        
        return "redirect:/listagem";
    }
    
    @PostMapping("/pesquisa")
    public String pesquisarFuncionario(Model model, @RequestParam String cpf){
        model.addAttribute("funcionarios", funcionarioService.pesquisarFuncionarioPorCpf(cpf));
        
        return "listarView";
    }
}

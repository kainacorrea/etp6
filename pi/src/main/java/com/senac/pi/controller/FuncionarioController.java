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
}

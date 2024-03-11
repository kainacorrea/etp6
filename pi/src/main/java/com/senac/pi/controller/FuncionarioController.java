package com.senac.pi.controller;

import com.senac.pi.data.FuncionarioEntity;
import com.senac.pi.data.PreferenciaEntity;
import com.senac.pi.service.FuncionarioService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FuncionarioController {
    
    @Autowired
    FuncionarioService funcionarioService;
            
    @GetMapping("/")
    public String indexPagina (Model model, @CookieValue(name="temaUsuario", defaultValue="claro") String tema){
        model.addAttribute("css", tema);
        return "index";
    }
    
    @GetMapping("/cadastro")
    public String cadastroFuncionario(Model model, @CookieValue(name="temaUsuario", defaultValue="claro") String tema){
        model.addAttribute("funcionario", new FuncionarioEntity());
        model.addAttribute("css", tema);
        return "cadastrarView";
    }
    
    @PostMapping("/cadastrar")
    public String processarCadastro(@ModelAttribute FuncionarioEntity f, Model model){
        funcionarioService.cadastrarFuncionario(f);
        
        return "redirect:/";
    }
    
    @GetMapping("/listagem")
    public String listagemFuncionarios(Model model, @CookieValue(name="temaUsuario", defaultValue="claro") String tema){
        model.addAttribute("funcionarios", funcionarioService.getTodosFuncionarios());
        model.addAttribute("css", tema);
        return "listarView";
    }
    
    @GetMapping("/detalhes")
    public String mostrarDetalhes(@RequestParam String id, Model model, @CookieValue(name="temaUsuario", defaultValue="claro") String tema){
        model.addAttribute("funcionario", funcionarioService.getFuncionarioPorId(Integer.parseInt(id)));
        model.addAttribute("css", tema);
        return "mostrarView";
    }
    
    @GetMapping("/alterar")
    public String alterarFuncionario(Model model, @RequestParam String id, @CookieValue(name="temaUsuario", defaultValue="claro") String tema){
        model.addAttribute("funcionario", funcionarioService.getFuncionarioPorId(Integer.parseInt(id)));
        model.addAttribute("css", tema);
         
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
    public String pesquisarFuncionario(Model model, @RequestParam String cpf, @CookieValue(name="temaUsuario", defaultValue="claro") String tema){
        model.addAttribute("funcionarios", funcionarioService.pesquisarFuncionarioPorCpf(cpf));
        model.addAttribute("css", tema);
        return "listarView";
    }
    
    @PostMapping("/preferencia")
    public ModelAndView salvarPreferencia(@ModelAttribute PreferenciaEntity p, HttpServletResponse response){
        Cookie cookieTema = new Cookie("temaUsuario", p.getEstilo());
        cookieTema.setDomain("localhost");
        cookieTema.setHttpOnly(true);
        cookieTema.setMaxAge(6400);
        
        response.addCookie(cookieTema);
        return new ModelAndView("redirect:/");
    }
}

package com.senac.pi.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioEntity {
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String email;
    private String observacoes;
}

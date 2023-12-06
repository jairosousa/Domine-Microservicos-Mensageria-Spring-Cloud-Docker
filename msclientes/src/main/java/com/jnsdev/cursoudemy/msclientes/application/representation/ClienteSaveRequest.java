package com.jnsdev.cursoudemy.msclientes.application.representation;

import com.jnsdev.cursoudemy.msclientes.domain.Cliente;
import lombok.Data;

/**
 * @Autor Jairo Nascimento
 * @Created 06/12/2023 - 17:26
 */

@Data
public class ClienteSaveRequest {
    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel() {
        return new Cliente(cpf, nome, idade);
    }
}

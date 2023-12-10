package com.jnsdev.cursoudemy.msavaliadorcredito.exception;

/**
 * @Autor Jairo Nascimento
 * @Created 10/12/2023 - 06:56
 */
public class DadosClienteNotFoundException extends Exception{

    public DadosClienteNotFoundException(String cpf) {
        super(String.format("Dados do cliente não encontrado para o CPF informado: %s", cpf));
    }
}

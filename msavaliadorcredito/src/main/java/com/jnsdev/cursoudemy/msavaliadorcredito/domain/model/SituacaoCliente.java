package com.jnsdev.cursoudemy.msavaliadorcredito.domain.model;

import lombok.Data;

import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 09/12/2023 - 08:33
 */
@Data
public class SituacaoCliente {
    private DadosCliente cliente;
    private List<CartaoCliente> cartoes;
}

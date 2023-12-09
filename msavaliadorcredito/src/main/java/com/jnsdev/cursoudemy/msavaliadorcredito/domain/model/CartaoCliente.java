package com.jnsdev.cursoudemy.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Autor Jairo Nascimento
 * @Created 09/12/2023 - 08:36
 */

@Data
public class CartaoCliente {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;
}

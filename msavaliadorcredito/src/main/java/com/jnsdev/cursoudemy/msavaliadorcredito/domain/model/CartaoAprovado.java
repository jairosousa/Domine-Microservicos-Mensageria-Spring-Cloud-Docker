package com.jnsdev.cursoudemy.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Autor Jairo Nascimento
 * @Created 10/12/2023 - 10:06
 */

@Data
public class CartaoAprovado {
    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;
}

package com.jnsdev.cursoudemy.msavaliadorcredito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 10/12/2023 - 10:05
 */
@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente {
    List<CartaoAprovado> cartoes;
}

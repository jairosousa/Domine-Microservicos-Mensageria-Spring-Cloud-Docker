package com.jnsdev.cursoudemy.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Autor Jairo Nascimento
 * @Created 10/12/2023 - 14:24
 */
@Data
public class DadosSolicitacaoEmissaoCartao {
    private Long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}

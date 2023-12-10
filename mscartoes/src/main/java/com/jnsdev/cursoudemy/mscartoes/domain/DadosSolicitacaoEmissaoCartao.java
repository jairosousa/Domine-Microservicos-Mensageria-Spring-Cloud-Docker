package com.jnsdev.cursoudemy.mscartoes.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Autor Jairo Nascimento
 * @Created 10/12/2023 - 14:52
 */
@Data
public class DadosSolicitacaoEmissaoCartao {
    private Long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}

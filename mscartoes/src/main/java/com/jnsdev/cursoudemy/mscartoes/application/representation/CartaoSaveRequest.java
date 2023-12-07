package com.jnsdev.cursoudemy.mscartoes.application.representation;

import com.jnsdev.cursoudemy.mscartoes.domain.BandeiraCartao;
import com.jnsdev.cursoudemy.mscartoes.domain.Cartao;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Autor Jairo Nascimento
 * @Created 07/12/2023 - 09:25
 */

@Data
public class CartaoSaveRequest {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel() {
        return new Cartao(nome, bandeira, renda, limite);
    }
}

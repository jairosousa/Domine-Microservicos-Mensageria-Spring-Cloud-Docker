package com.jnsdev.cursoudemy.msavaliadorcredito.exception;

import lombok.Getter;

/**
 * @Autor Jairo Nascimento
 * @Created 10/12/2023 - 07:03
 */
public class ErroCominicacaoMicroservicesException extends Exception{

    @Getter
    private Integer status;

    public ErroCominicacaoMicroservicesException(String mensagem, Integer status) {
        super(mensagem);
        this.status = status;
    }
}

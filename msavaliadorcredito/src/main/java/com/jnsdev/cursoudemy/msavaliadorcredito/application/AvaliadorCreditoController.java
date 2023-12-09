package com.jnsdev.cursoudemy.msavaliadorcredito.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Autor Jairo Nascimento
 * @Created 09/12/2023 - 08:16
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    @GetMapping
    public String status() {
        log.info("Obtendo o status do microserevice de avaliador de credito");
        return "OK";
    }
}

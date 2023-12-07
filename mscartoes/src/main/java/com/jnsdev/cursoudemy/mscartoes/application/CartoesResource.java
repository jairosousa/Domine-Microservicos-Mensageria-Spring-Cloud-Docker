package com.jnsdev.cursoudemy.mscartoes.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Autor Jairo Nascimento
 * @Created 07/12/2023 - 08:38
 */

@Slf4j
@RestController
@RequestMapping("cartoes")
public class CartoesResource {

    @GetMapping
    public String status() {
        log.info("Obtendo o status do microserevice de cartões");
        return "OK";
    }
}

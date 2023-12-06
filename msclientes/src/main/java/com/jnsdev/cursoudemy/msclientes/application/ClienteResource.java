package com.jnsdev.cursoudemy.msclientes.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Autor Jairo Nascimento
 * @Created 06/12/2023 - 17:13
 */

@RestController
@RequestMapping("clientes")
public class ClienteResource {

    @GetMapping
    public String status() {
        return "OK";
    }
}

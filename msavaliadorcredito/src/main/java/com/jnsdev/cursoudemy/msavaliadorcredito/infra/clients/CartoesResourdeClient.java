package com.jnsdev.cursoudemy.msavaliadorcredito.infra.clients;

import com.jnsdev.cursoudemy.msavaliadorcredito.domain.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 10/12/2023 - 06:38
 */

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesResourdeClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@RequestParam("cpf")String cpf);
}

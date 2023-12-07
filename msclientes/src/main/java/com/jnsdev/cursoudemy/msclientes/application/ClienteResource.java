package com.jnsdev.cursoudemy.msclientes.application;

import com.jnsdev.cursoudemy.msclientes.application.representation.ClienteSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @Autor Jairo Nascimento
 * @Created 06/12/2023 - 17:13
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("clientes")
public class ClienteResource {

    private final ClienteService service;

    @GetMapping
    public String status() {
        log.info("Obtendo o status do microserevice de cliente");
        return "OK";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request) {
        var cliente = request.toModel();
        service.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf) {
        var cliente = service.getByCPF(cpf);

        if (cliente.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(cliente);
    }
}

package com.jnsdev.cursoudemy.msclientes.application;

import com.jnsdev.cursoudemy.msclientes.domain.Cliente;
import com.jnsdev.cursoudemy.msclientes.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @Autor Jairo Nascimento
 * @Created 06/12/2023 - 17:18
 */

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional
    public Cliente save (Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> getByCPF(String cpf) {
        return repository.findByCpf(cpf);
    }
}

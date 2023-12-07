package com.jnsdev.cursoudemy.mscartoes.application;

import com.jnsdev.cursoudemy.mscartoes.domain.ClienteCartao;
import com.jnsdev.cursoudemy.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 07/12/2023 - 14:14
 */

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listCartoesByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}

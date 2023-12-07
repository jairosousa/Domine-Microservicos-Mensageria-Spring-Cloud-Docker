package com.jnsdev.cursoudemy.mscartoes.infra.repository;

import com.jnsdev.cursoudemy.mscartoes.domain.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 07/12/2023 - 14:07
 */
public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
    List<ClienteCartao> findByCpf(String cpf);
}

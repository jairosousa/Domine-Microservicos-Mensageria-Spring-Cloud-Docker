package com.jnsdev.cursoudemy.msclientes.infra.repository;

import com.jnsdev.cursoudemy.msclientes.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Autor Jairo Nascimento
 * @Created 06/12/2023 - 17:12
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}

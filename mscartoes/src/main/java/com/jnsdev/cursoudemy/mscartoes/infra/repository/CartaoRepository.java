package com.jnsdev.cursoudemy.mscartoes.infra.repository;

import com.jnsdev.cursoudemy.mscartoes.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 07/12/2023 - 09:16
 */
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    List<Cartao> findByRendaLessThanEqual(BigDecimal renda);
}

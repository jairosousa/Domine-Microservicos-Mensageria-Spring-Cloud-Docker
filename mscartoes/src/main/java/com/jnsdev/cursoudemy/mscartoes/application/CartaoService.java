package com.jnsdev.cursoudemy.mscartoes.application;

import com.jnsdev.cursoudemy.mscartoes.domain.Cartao;
import com.jnsdev.cursoudemy.mscartoes.infra.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 07/12/2023 - 09:18
 */

@RequiredArgsConstructor
@Service
public class CartaoService {

    private final CartaoRepository repository;

    public Cartao save(Cartao cartao) {
        return repository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
        var rendaBigDecimal = BigDecimal.valueOf(renda);

        return repository.findByRendaLessThanEqual(rendaBigDecimal);

    }

}

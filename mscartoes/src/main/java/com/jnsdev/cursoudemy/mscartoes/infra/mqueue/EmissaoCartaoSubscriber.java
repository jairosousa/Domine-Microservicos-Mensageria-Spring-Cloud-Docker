package com.jnsdev.cursoudemy.mscartoes.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jnsdev.cursoudemy.mscartoes.domain.Cartao;
import com.jnsdev.cursoudemy.mscartoes.domain.ClienteCartao;
import com.jnsdev.cursoudemy.mscartoes.domain.DadosSolicitacaoEmissaoCartao;
import com.jnsdev.cursoudemy.mscartoes.infra.repository.CartaoRepository;
import com.jnsdev.cursoudemy.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @Autor Jairo Nascimento
 * @Created 10/12/2023 - 13:46
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EmissaoCartaoSubscriber {

    private final CartaoRepository cartaoRepository;
    private final ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
        log.info("Mensagem recebida: " + payload);
        try {
            var dados = new ObjectMapper().readValue(payload, DadosSolicitacaoEmissaoCartao.class);

            Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();

            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.getCpf());
            clienteCartao.setLimite(dados.getLimiteLiberado());
            clienteCartao.setEndereco(dados.getEndereco());

            clienteCartaoRepository.save(clienteCartao);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

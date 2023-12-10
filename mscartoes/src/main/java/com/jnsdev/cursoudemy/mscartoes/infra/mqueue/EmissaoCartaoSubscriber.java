package com.jnsdev.cursoudemy.mscartoes.infra.mqueue;

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
public class EmissaoCartaoSubscriber {

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
        log.info("Mensagem recebida: " + payload);
        System.out.println(payload);
    }
}

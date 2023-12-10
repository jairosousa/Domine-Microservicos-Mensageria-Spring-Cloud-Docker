package com.jnsdev.cursoudemy.msavaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Autor Jairo Nascimento
 * @Created 10/12/2023 - 14:27
 */

@Configuration
public class MQconfig {

    @Value("${mq.queues.emissao-cartoes}")
    private String emissaoCartoesFila;

    @Bean
    public Queue queueEmissaoCartoes() {
        return new Queue(emissaoCartoesFila, true);
    }
}

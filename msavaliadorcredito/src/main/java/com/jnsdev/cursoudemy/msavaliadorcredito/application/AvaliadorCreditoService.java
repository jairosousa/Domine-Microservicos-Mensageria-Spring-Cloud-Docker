package com.jnsdev.cursoudemy.msavaliadorcredito.application;

import com.jnsdev.cursoudemy.msavaliadorcredito.domain.model.DadosCliente;
import com.jnsdev.cursoudemy.msavaliadorcredito.domain.model.SituacaoCliente;
import com.jnsdev.cursoudemy.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @Autor Jairo Nascimento
 * @Created 09/12/2023 - 08:41
 */

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) {
        // obter dados cliente - MSCLIENTE
        // obter cartões do cliente - MSCARTOES
        ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);

        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .build();

    }
}

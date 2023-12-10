package com.jnsdev.cursoudemy.msavaliadorcredito.application;

import com.jnsdev.cursoudemy.msavaliadorcredito.domain.model.CartaoCliente;
import com.jnsdev.cursoudemy.msavaliadorcredito.domain.model.DadosCliente;
import com.jnsdev.cursoudemy.msavaliadorcredito.domain.model.SituacaoCliente;
import com.jnsdev.cursoudemy.msavaliadorcredito.exception.DadosClienteNotFoundException;
import com.jnsdev.cursoudemy.msavaliadorcredito.exception.ErroCominicacaoMicroservicesException;
import com.jnsdev.cursoudemy.msavaliadorcredito.infra.clients.CartoesResourdeClient;
import com.jnsdev.cursoudemy.msavaliadorcredito.infra.clients.ClienteResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 09/12/2023 - 08:41
 */

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteClient;
    private final CartoesResourdeClient cartoesClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroCominicacaoMicroservicesException {
        try {
            // obter dados cliente - MSCLIENTE
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);
            // obter cartões do cliente - MSCARTOES
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException(cpf);
            }

            throw new ErroCominicacaoMicroservicesException(e.getMessage(), status);
        }
    }
}

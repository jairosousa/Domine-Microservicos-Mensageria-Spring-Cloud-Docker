package com.jnsdev.cursoudemy.msavaliadorcredito.application;

import com.jnsdev.cursoudemy.msavaliadorcredito.domain.model.*;
import com.jnsdev.cursoudemy.msavaliadorcredito.exception.DadosClienteNotFoundException;
import com.jnsdev.cursoudemy.msavaliadorcredito.exception.ErroCominicacaoMicroservicesException;
import com.jnsdev.cursoudemy.msavaliadorcredito.exception.ErroSolicitacaoCartaoException;
import com.jnsdev.cursoudemy.msavaliadorcredito.infra.clients.CartoesResourdeClient;
import com.jnsdev.cursoudemy.msavaliadorcredito.infra.clients.ClienteResourceClient;
import com.jnsdev.cursoudemy.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Autor Jairo Nascimento
 * @Created 09/12/2023 - 08:41
 */

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteClient;
    private final CartoesResourdeClient cartoesClient;
    private final SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;

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

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundException, ErroCominicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesClient.getCartoesRendaAteh(renda);

            var listaCartoesAprovado = cartoesResponse.getBody().stream().map(cartao -> {
                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.getLimiteBanco();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                BigDecimal fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoCliente(listaCartoesAprovado);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException(cpf);
            }

            throw new ErroCominicacaoMicroservicesException(e.getMessage(), status);
        }
    }

    public ProtocoloSolicitacaoCartao solictarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {
        try {
            emissaoCartaoPublisher.solictarCartao(dados);
            return new ProtocoloSolicitacaoCartao(UUID.randomUUID().toString());
        } catch (Exception ex) {
            throw new ErroSolicitacaoCartaoException(ex.getMessage());
        }
    }
}

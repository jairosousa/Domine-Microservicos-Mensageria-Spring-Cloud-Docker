package com.jnsdev.cursoudemy.msavaliadorcredito.application;

import com.jnsdev.cursoudemy.msavaliadorcredito.domain.model.SituacaoCliente;
import com.jnsdev.cursoudemy.msavaliadorcredito.exception.DadosClienteNotFoundException;
import com.jnsdev.cursoudemy.msavaliadorcredito.exception.ErroCominicacaoMicroservicesException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Autor Jairo Nascimento
 * @Created 09/12/2023 - 08:16
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping
    public String status() {
        log.info("Obtendo o status do microserevice de avaliador de credito");
        return "OK";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (ErroCominicacaoMicroservicesException e) {
            log.error(e.getMessage(),e.getStatus());
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}

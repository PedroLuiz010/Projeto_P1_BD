package pizzaria.projeto.pizza.controller.impl;

import pizzaria.projeto.pizza.controller.EntregaController;
import pizzaria.projeto.pizza.service.EntregaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EntregaControllerImpl implements EntregaController {

    private final EntregaService entregaService;

    @Override
    public ResponseEntity<Void> confirmarRecebimento(Long entregaId, String codigoDeEntrega) {
        entregaService.confirmarRecebimento(entregaId, codigoDeEntrega);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Boolean> verificarPagamento(Long entregaId) {
        boolean pagamentoConfirmado = entregaService.verificarPagamento(entregaId);
        return ResponseEntity.ok(pagamentoConfirmado);
    }
}

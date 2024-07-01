package pizzaria.projeto.pizza.controller.impl;

import pizzaria.projeto.pizza.controller.PedidoController;
import pizzaria.projeto.pizza.domain.entity.Pedido;
import pizzaria.projeto.pizza.service.PedidoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import java.util.List;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class PedidoControllerImpl implements PedidoController {

    private final PedidoService pedidoService;

    @Override
    public ResponseEntity<List<Pedido>> getPedidos(Long clienteId) {
        return ok(pedidoService.getPedidos(clienteId));
    }

    @Override
    public ResponseEntity<Void> adicionarPedido(Long clienteId, Pedido pedido) {
        return ok(pedidoService.adicionarPedido(clienteId, pedido));
    }

    @Override
    public ResponseEntity<BigDecimal> calcularTotal(Long pedidoId) {
        BigDecimal total = pedidoService.calcularTotal(pedidoId);
        return ok(total);
    }

    @Override
    public ResponseEntity<String> verificarStatusEntrega(Long pedidoId) {
        String status = pedidoService.verificarStatusEntrega(pedidoId);
        return ok(status);
    }
}

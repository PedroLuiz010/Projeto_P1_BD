package pizzaria.projeto.pizza.service;

import java.math.BigDecimal;
import java.util.List;

import pizzaria.projeto.pizza.entity.Pedido;

public interface PedidoService {
    BigDecimal calcularTotal(Long pedidoId);

    String verificarStatusEntrega(Long pedidoId);

    Void adicionarPedido(Long clienteId, Pedido pedido);

    List<Pedido> getPedidos(Long clienteId);
}

package pizzaria.projeto.pizza.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pizzaria.projeto.pizza.entity.Cliente;
import pizzaria.projeto.pizza.entity.Entrega;
import pizzaria.projeto.pizza.entity.Pedido;
import pizzaria.projeto.pizza.util.CodigoEntregaGenerator;
import pizzaria.projeto.pizza.repository.ClienteRepository;
import pizzaria.projeto.pizza.repository.PedidoRepository;
import pizzaria.projeto.pizza.service.PedidoService;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public BigDecimal calcularTotal(Long pedidoId) {
        Pedido pedido = pedidoRepository
                .findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        return pedido.getItensPedido().stream()
                .map(item -> item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String verificarStatusEntrega(Long pedidoId) {
        Pedido pedido = pedidoRepository
                .findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        Entrega entrega = pedido.getEntrega();
        if (entrega != null) {
            return "Entrega programada para " + entrega.getDataEntrega() + " às " + entrega.getHoraEntrega()
                    + " no endereço " + entrega.getEnderecoEntrega();
        } else {
            return "Pedido ainda não enviado para entrega";
        }
    }

    @Override
    public Void adicionarPedido(Long clienteId, Pedido pedido) {
        Cliente cliente = clienteRepository
                .findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        pedido.setCliente(cliente);
        pedido.getEntrega().setCodigoDeEntrega(CodigoEntregaGenerator.generateCodigoEntrega());
        pedidoRepository.save(pedido);
        return null;
    }

    @Override
    public List<Pedido> getPedidos(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }
}

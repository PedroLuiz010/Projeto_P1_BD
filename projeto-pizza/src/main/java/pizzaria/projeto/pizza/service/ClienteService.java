package pizzaria.projeto.pizza.service;

import java.util.List;

import pizzaria.projeto.pizza.entity.Cliente;
import pizzaria.projeto.pizza.entity.Pedido;
import pizzaria.projeto.pizza.request.ClienteRequest;
import pizzaria.projeto.pizza.response.ClienteResponse;

public interface ClienteService {
    void adicionarPedido(Long clienteId, Pedido pedido);
    List<Pedido> getPedidos(Long clienteId);
    Cliente buscarClientePorId(Long clienteId);
    Cliente atualizarCliente(Long clienteId, Cliente clienteAtualizado);
    void deletarCliente(Long clienteId);
    ClienteResponse criarCliente(ClienteRequest cliente);
    List<ClienteResponse> buscarTodosClientes();
}

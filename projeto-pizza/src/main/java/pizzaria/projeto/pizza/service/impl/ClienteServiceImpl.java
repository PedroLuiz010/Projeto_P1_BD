package pizzaria.projeto.pizza.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizzaria.projeto.pizza.entity.Cliente;
import pizzaria.projeto.pizza.entity.Pedido;
import pizzaria.projeto.pizza.repository.ClienteRepository;
import pizzaria.projeto.pizza.service.ClienteService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public void adicionarPedido(Long clienteId, Pedido pedido) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        pedido.setCliente(cliente);
        cliente.getPedidos().add(pedido);
        clienteRepository.save(cliente);
    }

    @Override
    public List<Pedido> getPedidos(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return cliente.getPedidos();
    }

    @Override
    public Cliente buscarClientePorId(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    @Override
    public Cliente atualizarCliente(Long clienteId, Cliente clienteAtualizado) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEndereco(clienteAtualizado.getEndereco());
        cliente.setTelefone(clienteAtualizado.getTelefone());
        return clienteRepository.save(cliente);
    }

    @Override
    public void deletarCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }

    @Override
    public ClienteResponse criarCliente(ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.map(clienteRequest);
        Cliente saved = clienteRepository.save(cliente);
        return clienteMapper.map(saved);
    }

    @Override
    public List<ClienteResponse> buscarTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(clienteMapper::map).toList();
    }
}

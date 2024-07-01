package pizzaria.projeto.pizza.controller.impl;

import pizzaria.projeto.pizza.controller.ClienteController;
import pizzaria.projeto.pizza.entity.Cliente;
import pizzaria.projeto.pizza.request.ClienteRequest;
import pizzaria.projeto.pizza.response.ClienteResponse;
import pizzaria.projeto.pizza.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ClienteControllerImpl implements ClienteController {

    private final ClienteService clienteService;

    @Override
    public ResponseEntity<ClienteResponse> criarCliente(ClienteRequest clienteRequest) {
        ClienteResponse cliente = clienteService.criarCliente(clienteRequest);
        return ResponseEntity.ok(cliente);
    }

    @Override
    public ResponseEntity<Cliente> buscarClientePorId(Long clienteId) {
        return ResponseEntity.ok(clienteService.buscarClientePorId(clienteId));
    }

    @Override
    public ResponseEntity<List<ClienteResponse>> buscarTodosClientes() {
        return ResponseEntity.ok(clienteService.buscarTodosClientes());
    }

    @Override
    public ResponseEntity<Cliente> atualizarCliente(Long clienteId, Cliente clienteAtualizado) {
        return ResponseEntity.ok(clienteService.atualizarCliente(clienteId, clienteAtualizado));
    }

    @Override
    public ResponseEntity<Void> deletarCliente(Long clienteId) {
        clienteService.deletarCliente(clienteId);
        return ResponseEntity.noContent().build();
    }
}

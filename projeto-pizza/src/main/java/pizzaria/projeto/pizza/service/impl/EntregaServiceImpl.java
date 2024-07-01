package pizzaria.projeto.pizza.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pizzaria.projeto.pizza.repository.EntregaRepository;
import pizzaria.projeto.pizza.service.EntregaService;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntregaServiceImpl implements EntregaService {

    private final EntregaRepository entregaRepository;

    @Override
    public void confirmarRecebimento(Long entregaId, String codigoDeEntrega) {
        Entrega entrega = entregaRepository
                .findById(entregaId)
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));
        if (!entrega.getCodigoDeEntrega().equals(codigoDeEntrega)) {
            throw new RuntimeException("Código de entrega inválido");
        }
        entrega.setStatusRecebido(true);
        entregaRepository.save(entrega);
    }

    @Override
    public boolean verificarPagamento(Long entregaId) {
        Entrega entrega = entregaRepository
                .findById(entregaId)
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));
        return entrega.isPagamentoConfirmado();
    }
}

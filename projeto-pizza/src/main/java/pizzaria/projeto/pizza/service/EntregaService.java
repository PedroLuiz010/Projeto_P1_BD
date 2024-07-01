package pizzaria.projeto.pizza.service;

public interface EntregaService {
    void confirmarRecebimento(Long entregaId, String codigoDeEntrega);

    boolean verificarPagamento(Long entregaId);
}

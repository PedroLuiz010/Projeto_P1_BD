package pizzaria.projeto.pizza;

import pizzaria.projeto.pizza.entity.Cliente;
import pizzaria.projeto.pizza.entity.Entrega;
import pizzaria.projeto.pizza.entity.Pedido;
import pizzaria.projeto.pizza.entity.Pizza;
import pizzaria.projeto.pizza.service.ClienteService;
import pizzaria.projeto.pizza.service.EntregaService;
import pizzaria.projeto.pizza.service.ItemService;
import pizzaria.projeto.pizza.service.PedidoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Configuration
@Profile("java")
public class InitDB implements CommandLineRunner {

    private final ClienteService clienteService;
    private final EntregaService entregaService;
    private final ItemService itemService;
    private final PedidoService pedidoService;

    public InitDB(ClienteService clienteService, EntregaService entregaService, ItemService itemService, PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.entregaService = entregaService;
        this.itemService = itemService;
        this.pedidoService = pedidoService;
    }

    @Override
    public void run(String... args) {
        final int QUANTIDADE_PEDIDOS_CLIENTE = 3;
        final int QUANTIDADE_ITENS_PEDIDO = 3;

        List<Cliente> clientes = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();
        List<Item> itens = new ArrayList<>();

        final Pizza[] sabores = {
                new Pizza("Pepperoni", new BigDecimal(35)),
                new Pizza("Vegetariana", new BigDecimal(32)),
                new Pizza("Napolitana", new BigDecimal(37)),
                new Pizza("Banana com Canela", new BigDecimal(48)),
                new Pizza("Romeu e Julieta", new BigDecimal(50))
        };

        Cliente cliente1 = new Cliente("101", "Rua Oeste", "919191919", "angelica", 1L);
        clientes.add(cliente1);
        clienteService.criarCliente(cliente1);
        Cliente cliente2 = new Cliente("202", "Rua Sul", "929292929", "João", 2L);
        clientes.add(cliente2);
        clienteService.criarCliente(cliente2);
        Cliente cliente3 = new Cliente("303", "Avenida são joao", "939334439", "carlos", 3L);
        clientes.add(cliente3);
        clienteService.criarCliente(cliente3);

        Entrega entrega = new Entrega(1L, new Date(), new Date(), "dinheiro", "2");
        entregaService.criarEntrega(entrega);

        for (int i = 0; i < clientes.size(); i++) {
            for (int j = 1; j <= QUANTIDADE_PEDIDOS_CLIENTE; j++) {
                long id = i * QUANTIDADE_PEDIDOS_CLIENTE + j;
                Pedido pedido = new Pedido(id, new Date(), new Date(), randInt(1, 5), new BigDecimal((i * 3) + j + 2), clientes.get(i), entrega);
                pedidos.add(pedido);
            }
        }

        for (int i = 0; i < pedidos.size(); i++) {
            BigDecimal valorTotal = new BigDecimal(0);
            int quantidadeItens = 0;
            for (int j = 1; j <= QUANTIDADE_ITENS_PEDIDO; j++) {
                long id = i * QUANTIDADE_ITENS_PEDIDO + j;
                Pizza pizza = sabores[randInt(0, sabores.length - 1)];
                int quantidade = randInt(1, 5);
                Item item = new Item(id, "Pizza de " + pizza.getSabor(), pizza.getValor(), quantidade, pedidos.get(i));
                itens.add(item);
                valorTotal = valorTotal.add(pizza.getValor().multiply(new BigDecimal(quantidade)));
                quantidadeItens += 1;
            }
            pedidos.get(i).setValorTotal(valorTotal);
            pedidos.get(i).setQuantidadeItens(quantidadeItens);
        }

        for (Pedido pedido : pedidos) {
            pedidoService.criarPedido(pedido);
        }

        for (Item item : itens) {
            itemService.criarItem(item);
        }
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}


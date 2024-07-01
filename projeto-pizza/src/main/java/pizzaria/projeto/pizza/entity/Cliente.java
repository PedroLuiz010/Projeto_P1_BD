package pizzaria.projeto.pizza.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String endereco;
    private String codigoDeEntrega;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;

    public Cliente(Long id, String nome, String telefone, String endereco, String codigoDeEntrega) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.codigoDeEntrega = codigoDeEntrega;
    }

    public Cliente(String nome, String telefone, String endereco, String codigoDeEntrega) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.codigoDeEntrega = codigoDeEntrega;
    }
}



package pizzaria.projeto.pizza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Pizza {
    private String sabor;
    private BigDecimal preço;

    public Pizza(String sabor, BigDecimal preço) {
        this.sabor = sabor;
        this.preço = preço;
    }

    public Object findById(Long clienteId) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}

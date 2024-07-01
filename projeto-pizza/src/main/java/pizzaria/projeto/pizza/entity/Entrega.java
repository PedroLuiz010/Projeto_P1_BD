package pizzaria.projeto.pizza.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entrega {

    private static final String Generated = null;

    @Id
    @GeneratedValue(strategy = Generated.IDENTITY)
    private Long id;

    private LocalDate dataEntrega;
    private LocalTime horaEntrega;
    private String tipoPagamento;
    private String codigoDeEntrega;
    private String enderecoEntrega;
    private boolean statusRecebido;
    private boolean pagamentoConfirmado;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Object getCodigoEntrega() {   
        throw new UnsupportedOperationException("Unimplemented method 'getCodigoEntrega'");
    }
    public void setCodigoEntrega(Object codigoEntrega) {
        throw new UnsupportedOperationException("Unimplemented method 'setCodigoEntrega'");
    }
    public Entrega(long l, Date date, Date date2, String string, String string2) {
    }
}

package pizzaria.projeto.pizza.controller;

import pizzaria.projeto.pizza.entity.Entrega;
import pizzaria.projeto.pizza.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/entrega")
public class EntregaController {

    @Autowired
    private EntregaRepository entregaRepository;

    @GetMapping
    public ResponseEntity<List<Entrega>> getAllEntrega() {
        List<Entrega> entregas = entregaRepository.findAll();
        return new ResponseEntity<>(entregas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> getEntregaById(@PathVariable Long id) {
        Optional<Entrega> entrega = entregaRepository.findById(id);
        if (entrega.isPresent()) {
            return new ResponseEntity<>(entrega.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Entrega> createEntrega(@RequestBody Entrega entrega) {
        Entrega novaEntrega = entregaRepository.save(entrega);
        return new ResponseEntity<>(novaEntrega, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrega> updateEntrega(@PathVariable Long id, @RequestBody Entrega entregaDetails) {
        Optional<Entrega> entregaOptional = entregaRepository.findById(id);
        if (entregaOptional.isPresent()) {
            Entrega entrega = entregaOptional.get();
            entrega.setDataEntrega(entregaDetails.getDataEntrega());
            entrega.setHoraEntrega(entregaDetails.getHoraEntrega());
            entrega.setTipoPagamento(entregaDetails.getTipoPagamento());
            entrega.setCodigoEntrega(entregaDetails.getCodigoEntrega());
            Entrega updatedEntrega = entregaRepository.save(entrega);
            return new ResponseEntity<>(updatedEntrega, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrega(@PathVariable Long id) {
        if (entregaRepository.existsById(id)) {
            entregaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

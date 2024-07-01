package pizzaria.projeto.pizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pizzaria.projeto.pizza.entity.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {}

package br.com.udesc.apiroboticscompetition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.udesc.apiroboticscompetition.model.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Long>{

}

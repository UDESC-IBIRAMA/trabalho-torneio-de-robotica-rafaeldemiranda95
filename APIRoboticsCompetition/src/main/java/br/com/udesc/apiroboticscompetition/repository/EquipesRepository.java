package br.com.udesc.apiroboticscompetition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.udesc.apiroboticscompetition.model.Equipes;

public interface EquipesRepository extends JpaRepository<Equipes, Long> {

}

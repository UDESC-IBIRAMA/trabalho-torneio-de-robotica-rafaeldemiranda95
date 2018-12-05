package br.com.udesc.apiroboticscompetition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.udesc.apiroboticscompetition.model.Competicao;

public interface CompeticaoRepository extends JpaRepository<Competicao, Long>{

}

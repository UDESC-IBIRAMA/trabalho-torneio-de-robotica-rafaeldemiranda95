package br.com.udesc.apiroboticscompetition.rest;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.udesc.apiroboticscompetition.model.Criterios;
import br.com.udesc.apiroboticscompetition.repository.CriteriosRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/criterios")
public class CriteriosResource {

	@Autowired
	private CriteriosRepository cr;

	@GetMapping(value = "/teste", produces = "application/json")
	public @ResponseBody Criterios testeCriterios() {

		Criterios oCriterios = new Criterios();
		oCriterios.setId(1L);
		oCriterios.setNome("Prime");
		oCriterios.setDescricao("Criterio desc");
		oCriterios.setCategoriaId((long) 1);

		return oCriterios;
	}

	@GetMapping(value = "/{id}")
	public Criterios buscaId(@PathVariable Long id) throws Exception {
		Optional<Criterios> verifica = cr.findById(id);
		if (!verifica.isPresent()) {
			throw new Exception("Id - " + id + " Inexistente!");
		}
		return verifica.get();
	}

	@GetMapping(value = "/lista")
	public @ResponseBody Iterable<Criterios> listaCriterios() {
		Iterable<Criterios> listaCriterios = cr.findAll();
		return listaCriterios;
	}

	@PostMapping(value = "/adiciona")
	public Criterios cadastraCriterios(@RequestBody @Valid Criterios oCriterios) {
		return cr.save(oCriterios);
	}

	@PutMapping(value = "/modifica/{id}")
	public String modificaCriterios(@PathVariable("id") Long id, @RequestBody Criterios oCriterios) {
		Optional<Criterios> criterioInfo = cr.findById(id);
		String mensagem = "";
		try {
			if (criterioInfo.isPresent()) {
				Criterios criterioAtual = criterioInfo.get();
				criterioAtual.setId(oCriterios.getId());
				criterioAtual.setNome(oCriterios.getNome());
				criterioAtual.setId(oCriterios.getCategoriaId());

				// altera os dados
				cr.save(criterioAtual);
				mensagem = "OK";
			} else {
				mensagem = "Id Inexistente!";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return mensagem;
	}

	@DeleteMapping(value = "/deleta/{id}")
	public String removerCriterios(@PathVariable Long id) {
		Optional<Criterios> existe = cr.findById(id);
		String feedBack = "";
		try {
			if (existe.isPresent()) {
				Criterios removeCriterio = existe.get();
				cr.delete(removeCriterio);
				feedBack = "OK";
			} else {
				feedBack = "Id Inexistente!";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return feedBack;
	}

}

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
import br.com.udesc.apiroboticscompetition.model.Equipes;
import br.com.udesc.apiroboticscompetition.repository.EquipesRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/equipes")
public class EquipesResource {

	@Autowired
	private EquipesRepository er;

	@GetMapping(value = "/teste", produces = "application/json")
	public @ResponseBody Equipes testeEquipes() {

		Equipes oEquipes = new Equipes();
		oEquipes.setId(1L);
		oEquipes.setNome("Prime");
		oEquipes.setCompeticaoId((long) 1);
		
		return oEquipes;
	}

	@GetMapping(value = "/{id}")
	public Equipes buscaId(@PathVariable Long id) throws Exception {
		Optional<Equipes> verifica = er.findById(id);
		if (!verifica.isPresent()) {
			throw new Exception("Id - " + id + " Inexistente!");
		}
		return verifica.get();
	}

	@GetMapping(value = "/lista")
	public @ResponseBody Iterable<Equipes> listaEquipes() {
		Iterable<Equipes> listaEquipes = er.findAll();
		return listaEquipes;
	}

	@PostMapping(value = "/adiciona")
	public Equipes cadastraEquipes(@RequestBody @Valid Equipes oEquipes) {
		return er.save(oEquipes);
	}

	@PutMapping(value = "/modifica/{id}")
	public String modificaEquipes(@PathVariable("id") Long id, @RequestBody Equipes oEquipes) {
		Optional<Equipes> equipeInfo = er.findById(id);
		String mensagem = "";
		try {
			if (equipeInfo.isPresent()) {
				Equipes equipeAtual = equipeInfo.get();
				equipeAtual.setId(oEquipes.getId());
				equipeAtual.setNome(oEquipes.getNome());

				// altera os dados
				er.save(equipeAtual);
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
	public String removerEquipes(@PathVariable Long id) {
		Optional<Equipes> existe = er.findById(id);
		String feedBack = "";
		try {
			if (existe.isPresent()) {
				Equipes removeEquipe = existe.get();
				er.delete(removeEquipe);
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

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
import br.com.udesc.apiroboticscompetition.model.Salas;
import br.com.udesc.apiroboticscompetition.repository.SalasRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/salas")
public class SalasResource {

	@Autowired
	private SalasRepository sr;

	@GetMapping(value = "/teste", produces = "application/json")
	public @ResponseBody Salas testeSalas() {

		Salas oSalas = new Salas();
		oSalas.setId(1L);
		oSalas.setNome("Prime");
		oSalas.setCompeticaoId((long) 1);
		oSalas.setCategoriaId((long) 1);
		return oSalas;
	}

	@GetMapping(value = "/{id}")
	public Salas buscaId(@PathVariable Long id) throws Exception {
		Optional<Salas> verifica = sr.findById(id);
		if (!verifica.isPresent()) {
			throw new Exception("Id - " + id + " Inexistente!");
		}
		return verifica.get();
	}

	@GetMapping(value = "/lista")
	public @ResponseBody Iterable<Salas> listaSalas() {
		Iterable<Salas> listaSalas = sr.findAll();
		return listaSalas;
	}

	@PostMapping(value = "/adiciona")
	public Salas cadastraSalas(@RequestBody @Valid Salas oSalas) {
		return sr.save(oSalas);
	}

	@PutMapping(value = "/modifica/{id}")
	public String modificaoSalas(@PathVariable("id") Long id, @RequestBody Salas oSalas) {
		Optional<Salas> salaInfo = sr.findById(id);
		String mensagem = "";
		try {
			if (salaInfo.isPresent()) {
				Salas salaAtual = salaInfo.get();
				salaAtual.setId(oSalas.getId());
				salaAtual.setNome(oSalas.getNome());
				salaAtual.setCompeticaoId(oSalas.getCompeticaoId());
				salaAtual.setCategoriaId(oSalas.getCategoriaId());

				// altera os dados
				sr.save(salaAtual);
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
	public String removerSalas(@PathVariable Long id) {
		Optional<Salas> existe = sr.findById(id);
		String feedBack = "";
		try {
			if (existe.isPresent()) {
				Salas removeSala = existe.get();
				sr.delete(removeSala);
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

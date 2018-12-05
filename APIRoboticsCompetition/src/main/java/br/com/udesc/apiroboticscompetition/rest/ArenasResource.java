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
import br.com.udesc.apiroboticscompetition.model.Arenas;
import br.com.udesc.apiroboticscompetition.repository.ArenasRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/arenas")
public class ArenasResource {

	@Autowired
	private ArenasRepository ar;

	@GetMapping(value = "/teste", produces = "application/json")
	public @ResponseBody Arenas testeArenas() {

		Arenas oArenas = new Arenas();
		oArenas.setId(1L);
		oArenas.setNome("Prime");
		oArenas.setCompeticaoId((long) 1);
		return oArenas;
	}

	@GetMapping(value = "/{id}")
	public Arenas buscaId(@PathVariable Long id) throws Exception {
		Optional<Arenas> verifica = ar.findById(id);
		if (!verifica.isPresent()) {
			throw new Exception("Id - " + id + " Inexistente!");
		}
		return verifica.get();
	}

	@GetMapping(value = "/lista")
	public @ResponseBody Iterable<Arenas> listaArenas() {
		Iterable<Arenas> listaArenas = ar.findAll();
		return listaArenas;
	}

	@PostMapping(value = "/adiciona")
	public Arenas cadastraArenas(@RequestBody @Valid Arenas oArenas) {
		return ar.save(oArenas);
	}

	@PutMapping(value = "/modifica/{id}")
	public String modificaoArenas(@PathVariable("id") Long id, @RequestBody Arenas oArenas) {
		Optional<Arenas> arenasInfo = ar.findById(id);
		String mensagem = "";
		try {
			if (arenasInfo.isPresent()) {
				Arenas arenaAtual = arenasInfo.get();
				arenaAtual.setId(oArenas.getId());
				arenaAtual.setNome(oArenas.getNome());
				arenaAtual.setCompeticaoId(oArenas.getCompeticaoId());

				// altera os dados
				ar.save(arenaAtual);
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
	public String removerArenas(@PathVariable Long id) {
		Optional<Arenas> existe = ar.findById(id);
		String feedBack = "";
		try {
			if (existe.isPresent()) {
				Arenas removeArena = existe.get();
				ar.delete(removeArena);
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

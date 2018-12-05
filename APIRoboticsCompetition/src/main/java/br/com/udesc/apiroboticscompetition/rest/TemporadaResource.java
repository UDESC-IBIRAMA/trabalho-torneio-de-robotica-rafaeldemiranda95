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
import br.com.udesc.apiroboticscompetition.model.Temporada;
import br.com.udesc.apiroboticscompetition.repository.TemporadaRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/temporadas")
public class TemporadaResource {

	@Autowired
	private TemporadaRepository tr;

	@GetMapping(value = "/teste", produces = "application/json")
	public @ResponseBody Temporada testeTemporadas() {

		Temporada oTemporada = new Temporada();
		oTemporada.setId(1L);
		oTemporada.setNome("Caça ao Coelho");
		oTemporada.setDescricao("Liberada sempre em julho.");
		return oTemporada;
	}

	@GetMapping(value = "/{id}")
	public Temporada buscaId(@PathVariable Long id) throws Exception {
		Optional<Temporada> verifica = tr.findById(id);
		if (!verifica.isPresent()) {
			throw new Exception("Id - " + id + " Inexistente!");
		}
		return verifica.get();
	}

	@GetMapping(value = "/lista")
	public @ResponseBody Iterable<Temporada> listaTemporadas() {
		Iterable<Temporada> listaTemporadas = tr.findAll();
		return listaTemporadas;
	}

	@PostMapping(value = "/adiciona")
	public Temporada cadastraTemporada(@RequestBody @Valid Temporada oTemporada) {
		return tr.save(oTemporada);
	}

	@PutMapping(value = "/modifica/{id}")
	public String modificaTemporada(@PathVariable("id") Long id, @RequestBody Temporada oTemporada) {
		Optional<Temporada> temporadaInfo = tr.findById(id);
		String mensagem = "";
		try {
			if (temporadaInfo.isPresent()) {
				Temporada tempAtual = temporadaInfo.get();
				tempAtual.setId(oTemporada.getId());
				tempAtual.setNome(oTemporada.getNome());
				tempAtual.setDescricao(oTemporada.getDescricao());

				// altera os dados
				tr.save(tempAtual);
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
	public String removerTemporada(@PathVariable Long id) {
		Optional<Temporada> existe = tr.findById(id);
		String feedBack = "";
		try {
			if (existe.isPresent()) {
				Temporada removeTemporada = existe.get();
				tr.delete(removeTemporada);
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

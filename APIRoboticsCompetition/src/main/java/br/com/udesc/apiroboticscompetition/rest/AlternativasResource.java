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
import br.com.udesc.apiroboticscompetition.model.Alternativas;
import br.com.udesc.apiroboticscompetition.repository.AlternativasRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/alternativas")
public class AlternativasResource {

	@Autowired
	private AlternativasRepository ar;

	@GetMapping(value = "/teste", produces = "application/json")
	public @ResponseBody Alternativas testeAlternativas() {

		Alternativas oAlternativas = new Alternativas();
		oAlternativas.setId(1L);
		oAlternativas.setValor(7.00);
		oAlternativas.setCriterioId((long) 1);

		return oAlternativas;
	}

	@GetMapping(value = "/{id}")
	public Alternativas buscaId(@PathVariable Long id) throws Exception {
		Optional<Alternativas> verifica = ar.findById(id);
		if (!verifica.isPresent()) {
			throw new Exception("Id - " + id + " Inexistente!");
		}
		return verifica.get();
	}

	@GetMapping(value = "/lista")
	public @ResponseBody Iterable<Alternativas> listaAlternativas() {
		Iterable<Alternativas> listaAlternativas = ar.findAll();
		return listaAlternativas;
	}

	@PostMapping(value = "/adiciona")
	public Alternativas cadastraAlternativas(@RequestBody @Valid Alternativas oAlternativas) {
		return ar.save(oAlternativas);
	}

	@PutMapping(value = "/modifica/{id}")
	public String modificaAlternativas(@PathVariable("id") Long id, @RequestBody Alternativas oAlternativas) {
		Optional<Alternativas> alternativasInfo = ar.findById(id);
		String mensagem = "";
		try {
			if (alternativasInfo.isPresent()) {
				Alternativas alternativaAtual = alternativasInfo.get();
				alternativaAtual.setId(oAlternativas.getId());
				alternativaAtual.setValor(oAlternativas.getValor());
				alternativaAtual.setCriterioId(oAlternativas.getCriterioId());

				// altera os dados
				ar.save(alternativaAtual);
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
	public String removerAlternativas(@PathVariable Long id) {
		Optional<Alternativas> existe = ar.findById(id);
		String feedBack = "";
		try {
			if (existe.isPresent()) {
				Alternativas removeAlternativas = existe.get();
				ar.delete(removeAlternativas);
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

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
import br.com.udesc.apiroboticscompetition.model.Competicao;
import br.com.udesc.apiroboticscompetition.repository.CompeticaoRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/competicoes")
public class CompeticaoResource {

	@Autowired
	private CompeticaoRepository cr;

	@GetMapping(value = "/teste", produces = "application/json")
	public @ResponseBody Competicao testeCompeticao() {

		Competicao oCompeticao = new Competicao();
		oCompeticao.setId(1L);
		oCompeticao.setCidade("Laurentino");
		oCompeticao.setUf("SC");
		oCompeticao.setDataEvento("2018-11-06");
		oCompeticao.setResponsavel("EU");
		oCompeticao.setTipo("Regional");
		oCompeticao.setTemporadaId((long) 1);
		
		return oCompeticao;
	}

	@GetMapping(value = "/{id}")
	public Competicao buscaId(@PathVariable Long id) throws Exception {
		Optional<Competicao> verifica = cr.findById(id);
		if (!verifica.isPresent()) {
			throw new Exception("Id - " + id + " Inexistente!");
		}
		return verifica.get();
	}

	@GetMapping(value = "/lista")
	public @ResponseBody Iterable<Competicao> listaCompeticoes() {
		Iterable<Competicao> listaCompeticoes = cr.findAll();
		return listaCompeticoes;
	}

	@PostMapping(value = "/adiciona")
	public Competicao cadastraCompeticao(@RequestBody @Valid Competicao oCompeticao) {
		return cr.save(oCompeticao);
	}

	@PutMapping(value = "/modifica/{id}")
	public String modificaCompeticao(@PathVariable("id") Long id, @RequestBody Competicao oCompeticao) {
		Optional<Competicao> competicaoInfo = cr.findById(id);
		String mensagem = "";
		try {
			if (competicaoInfo.isPresent()) {
				Competicao competicaoAtual = competicaoInfo.get();
				competicaoAtual.setId(oCompeticao.getId());
				competicaoAtual.setCidade(oCompeticao.getCidade());
				competicaoAtual.setUf(oCompeticao.getUf());
				competicaoAtual.setDataEvento(oCompeticao.getDataEvento());
				competicaoAtual.setResponsavel(oCompeticao.getResponsavel());
				competicaoAtual.setTipo(oCompeticao.getTipo());

				// altera os dados
				cr.save(competicaoAtual);
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
	public String removerCompeticao(@PathVariable Long id) {
		Optional<Competicao> existe = cr.findById(id);
		String feedBack = "";
		try {
			if (existe.isPresent()) {
				Competicao removeCompeticao = existe.get();
				cr.delete(removeCompeticao);
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

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
import br.com.udesc.apiroboticscompetition.model.Juizes;
import br.com.udesc.apiroboticscompetition.repository.JuizesRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/juizes")
public class JuizesResource {

	@Autowired
	private JuizesRepository jr;

	@GetMapping(value = "/teste", produces = "application/json")
	public @ResponseBody Juizes testeJuizes() {

		Juizes oJuizes = new Juizes();
		oJuizes.setId(1L);
		oJuizes.setNome("Prime");
		oJuizes.setRg("654654516");
		oJuizes.setCpf("65468468166");
		oJuizes.setDataNasc("21/04/1985");
		oJuizes.setCategoriaId((long) 1);

		return oJuizes;
	}

	@GetMapping(value = "/{id}")
	public Juizes buscaId(@PathVariable Long id) throws Exception {
		Optional<Juizes> verifica = jr.findById(id);
		if (!verifica.isPresent()) {
			throw new Exception("Id - " + id + " Inexistente!");
		}
		return verifica.get();
	}

	@GetMapping(value = "/lista")
	public @ResponseBody Iterable<Juizes> listaJuizes() {
		Iterable<Juizes> listaJuizes = jr.findAll();
		return listaJuizes;
	}

	@PostMapping(value = "/adiciona")
	public Juizes cadastraJuizes(@RequestBody @Valid Juizes oJuizes) {
		return jr.save(oJuizes);
	}

	@PutMapping(value = "/modifica/{id}")
	public String modificaJuizes(@PathVariable("id") Long id, @RequestBody Juizes oJuizes) {
		Optional<Juizes> juizInfo = jr.findById(id);
		String mensagem = "";
		try {
			if (juizInfo.isPresent()) {
				Juizes juizAtual = juizInfo.get();
				juizAtual.setId(oJuizes.getId());
				juizAtual.setNome(oJuizes.getNome());
				juizAtual.setRg(oJuizes.getRg());
				juizAtual.setCpf(oJuizes.getCpf());
				juizAtual.setDataNasc(oJuizes.getDataNasc());
				juizAtual.setCategoriaId(oJuizes.getCategoriaId());

				// altera os dados
				jr.save(juizAtual);
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
	public String removerJuizes(@PathVariable Long id) {
		Optional<Juizes> existe = jr.findById(id);
		String feedBack = "";
		try {
			if (existe.isPresent()) {
				Juizes removeJuiz = existe.get();
				jr.delete(removeJuiz);
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

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
import br.com.udesc.apiroboticscompetition.model.Integrantes;
import br.com.udesc.apiroboticscompetition.repository.IntegrantesRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/integrantes")
public class IntegrantesResource {

	@Autowired
	private IntegrantesRepository ir;

	@GetMapping(value = "/teste", produces = "application/json")
	public @ResponseBody Integrantes testeIntegrantes() {

		Integrantes oIntegrantes = new Integrantes();
		oIntegrantes.setId(1L);
		oIntegrantes.setRg("4154915");
		oIntegrantes.setCpf("04980134998");
		oIntegrantes.setDatNasc("21-04-1985");
		oIntegrantes.setNomeMae("Nome da Mãe");
		oIntegrantes.setAutorizado(true);
		oIntegrantes.setEquipeId((long) 1);
		
		return oIntegrantes;
	}

	@GetMapping(value = "/{id}")
	public Integrantes buscaId(@PathVariable Long id) throws Exception {
		Optional<Integrantes> verifica = ir.findById(id);
		if (!verifica.isPresent()) {
			throw new Exception("Id - " + id + " Inexistente!");
		}
		return verifica.get();
	}

	@GetMapping(value = "/lista")
	public @ResponseBody Iterable<Integrantes> listaIntegrantes() {
		Iterable<Integrantes> listaIntegrantes = ir.findAll();
		return listaIntegrantes;
	}

	@PostMapping(value = "/adiciona")
	public Integrantes cadastraIntegrantes(@RequestBody @Valid Integrantes oIntegrantes) {
		return ir.save(oIntegrantes);
	}

	@PutMapping(value = "/modifica/{id}")
	public String modificaIntegrantes(@PathVariable("id") Long id, @RequestBody Integrantes oIntegrantes) {
		Optional<Integrantes> integrantesInfo = ir.findById(id);
		String mensagem = "";
		try {
			if (integrantesInfo.isPresent()) {
				Integrantes integranteAtual = integrantesInfo.get();
				integranteAtual.setId(oIntegrantes.getId());
				integranteAtual.setRg(oIntegrantes.getRg());
				integranteAtual.setCpf(oIntegrantes.getCpf());
				integranteAtual.setDatNasc(oIntegrantes.getDatNasc());
				integranteAtual.setNomeMae(oIntegrantes.getNomeMae());
				integranteAtual.setAutorizado(oIntegrantes.isAutorizado());
				integranteAtual.setEquipeId(oIntegrantes.getEquipeId());

				// altera os dados
				ir.save(integranteAtual);
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
	public String removerIntegrantes(@PathVariable Long id) {
		Optional<Integrantes> existe = ir.findById(id);
		String feedBack = "";
		try {
			if (existe.isPresent()) {
				Integrantes removeIntegrante = existe.get();
				ir.delete(removeIntegrante);
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

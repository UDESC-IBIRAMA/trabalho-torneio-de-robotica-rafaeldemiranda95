package br.com.udesc.apiroboticscompetition.rest;

import java.util.Optional;
import javax.validation.Valid;

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
import br.com.udesc.apiroboticscompetition.model.Categorias;
import br.com.udesc.apiroboticscompetition.repository.CategoriasRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/categorias")
public class CategoriasResource {

	private CategoriasRepository cr;

	@GetMapping(value = "/teste", produces = "application/json")
	public @ResponseBody Categorias testeCategorias() {

		Categorias oCategorias = new Categorias();
		oCategorias.setId(1L);
		oCategorias.setNome("Prime");
		oCategorias.setDescricao("categoria sei lá");

		return oCategorias;
	}

	@GetMapping(value = "/{id}")
	public Categorias buscaId(@PathVariable Long id) throws Exception {
		Optional<Categorias> verifica = cr.findById(id);
		if (!verifica.isPresent()) {
			throw new Exception("Id - " + id + " Inexistente!");
		}
		return verifica.get();
	}

	@GetMapping(value = "/lista")
	public @ResponseBody Iterable<Categorias> listaCategorias() {
		Iterable<Categorias> listaCategorias = cr.findAll();
		return listaCategorias;
	}

	@PostMapping(value = "/adiciona")
	public Categorias cadastraCategorias(@RequestBody @Valid Categorias oCategorias) {
		return cr.save(oCategorias);
	}

	@PutMapping(value = "/modifica/{id}")
	public String modificaCategorias(@PathVariable("id") Long id, @RequestBody Categorias oCategorias) {
		Optional<Categorias> categoriaInfo = cr.findById(id);
		String mensagem = "";
		try {
			if (categoriaInfo.isPresent()) {
				Categorias categoriaAtual = categoriaInfo.get();
				categoriaAtual.setId(oCategorias.getId());
				categoriaAtual.setNome(oCategorias.getNome());
				categoriaAtual.setDescricao(oCategorias.getDescricao());

				// altera os dados
				cr.save(categoriaAtual);
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
	public String removerCategorias(@PathVariable Long id) {
		Optional<Categorias> existe = cr.findById(id);
		String feedBack = "";
		try {
			if (existe.isPresent()) {
				Categorias removeCategorias = existe.get();
				cr.delete(removeCategorias);
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

package com.mballen.curso.boot.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mballen.curso.boot.DataSourceModel.NutricionistaEntity;
import com.mballen.curso.boot.Exceptions.NutricionistaNotFoundException;
import com.mballen.curso.boot.Repositories.NutricionistaRepository;
import com.mballen.curso.boot.Resources.NutricionistaResource;
import com.mballen.curso.boot.Services.BuscarNutricionistaPorId;
import com.mballen.curso.boot.Services.BuscarTodosNutricionistas;
import com.mballen.curso.boot.Services.CadastroNutricionista;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = {"/Api-Nutricionistas","/api-nutricionistas","/API-NUTRICIONISTAS"})
@Api(value="API REST nutricionistas")
//quero que qualquer aplicação acesse esta api
@CrossOrigin(origins="*")
public class NutricionistaController {
	@Autowired
	private NutricionistaRepository Repository;
	@Autowired
	private BuscarTodosNutricionistas BuscarTodos;
	@Autowired
	private CadastroNutricionista cadastro;
	@Autowired
	private BuscarNutricionistaPorId buscarPorId;

	@GetMapping(path ="/buscarporid/{id}")
	@ApiOperation(value="Retorna um produto")
	public NutricionistaEntity buscarNutricionistaPorId(
			@PathVariable(name = "id", required = true) Long idnutricionista) throws NutricionistaNotFoundException {

		return buscarPorId.BuscarPorId(idnutricionista);

	}

	@GetMapping(path = "BuscarTodos")
	@ApiOperation(value="Retorna uma lista de  produtos")
	public List<NutricionistaEntity> buscarNutricionistas() {

		return BuscarTodos.BuscarTodos();

	}

	@PostMapping(path = "/salvar")
	@ApiOperation(value="Salva  um produto")
	public void salvarNutricionista(@RequestBody NutricionistaResource nutricionista) {

		cadastro.cadastro(nutricionista);
	}
	@PutMapping(path = "/editar/{id}")
	@ApiOperation(value="Atualiza  um produto")
	public void EditarNutricionista(@PathVariable(name = "id", required = true) Long idnutricionista,@RequestBody NutricionistaResource entity) {
	  cadastro.cadastro(entity);
	}

	@DeleteMapping(path = "/Deletar/{id}")
	@ApiOperation(value="Remove  um produto")
	public void DeletarNutricionista(@PathVariable(name = "id", required = true) Long idnutricionista) {
		Repository.deleteById(idnutricionista);
	}

}

package jv.triersistemas.primeiro_projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jv.triersistemas.primeiro_projeto.dto.CategoriaDto;
import jv.triersistemas.primeiro_projeto.service.CategoriaService;

@RestController
@RequestMapping("/categoria-controller")
public class CategoriaController {
	
	
	@Autowired
	private CategoriaService categoriaService;
	
	//Cadastrar Nova Categoria
	@PostMapping("/cadastro")
	public CategoriaDto salvarCategoria(@RequestBody CategoriaDto categoriaDto) {
		return categoriaService.salvarCategoria(categoriaDto);
	}
	
	
	//Buscar Todas as categorias
	@GetMapping("/buscaCadastro")
	public List<CategoriaDto> buscarTodasCategorias() {
		return categoriaService.buscarTodasCategoria();
	}
	
	//Buscar Categoria Específica
	@GetMapping("/buscaCadastro/{id}")
	public CategoriaDto buscaCategoria(@PathVariable Long id) {
		return categoriaService.buscarCategoria(id);
	}
	
	//Alterar Categoria
	@PutMapping("/atualizaCadastro/{id}")
	public CategoriaDto atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDto categoriaDto) {
		return categoriaService.atualizarCategoria(id, categoriaDto);
	}
	
	//Deletar Categoria
	@DeleteMapping("/deletaCadastro/{id}")
	public void deletarCategoria(@PathVariable Long id) {
		categoriaService.deletarCategoria(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

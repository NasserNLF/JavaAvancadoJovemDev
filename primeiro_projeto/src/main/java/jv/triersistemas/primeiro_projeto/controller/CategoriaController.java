package jv.triersistemas.primeiro_projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	@GetMapping("/busca-cadastro")
	public List<CategoriaDto> buscarTodasCategorias() {
		return categoriaService.buscarTodasCategoria();
	}
	
	//Buscar Categoria Específica
	@GetMapping("/busca-cadastro/{id}")
	public CategoriaDto buscaCategoria(@PathVariable Long id) {
		return categoriaService.buscarCategoria(id);
	}
	
	//Alterar Categoria
	@PutMapping("/atualiza-cadastro/{id}")
	public CategoriaDto atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDto categoriaDto) {
		return categoriaService.atualizarCategoria(id, categoriaDto);
	}
	
	//Deletar Categoria
	@DeleteMapping("/deleta-cadastro/{id}")
	public ResponseEntity<?> deletarCategoria(@PathVariable Long id) {
		try {
			categoriaService.deletarCategoria(id);
			return ResponseEntity.ok("Deletado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package jv.triersistemas.primeiro_projeto.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jv.triersistemas.primeiro_projeto.dto.TarefaDto;
import jv.triersistemas.primeiro_projeto.service.TarefaService;

@RestController
@RequestMapping("/tarefa-controller")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;

	// POST
	@PostMapping("/tarefas")
	public ResponseEntity<?> salvarTarefa(@RequestBody TarefaDto tarefa) {
		try {
			return ResponseEntity.ok(tarefaService.salvarTarefa(tarefa));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	// GET

	@GetMapping("/tarefas")
	public List<TarefaDto> buscarTodasTarefas() {
		return tarefaService.buscarTodasTarefas();
	}

	@GetMapping("/tarefas/{id}")
	public TarefaDto buscarTarefa(@PathVariable("id") Long id) {
		return tarefaService.buscarTarefa(id);
	}
	
	@GetMapping("/tarefas-incompletas/{id}")
	public List<TarefaDto> buscarTarefasIncompletas(@PathVariable  Long id){
		return tarefaService.buscarTarefasIncompletas(id);
	}
	
	@GetMapping("/tarefas-titulo/{titulo}")
	public ResponseEntity<?> buscarTarefaTitulo(@PathVariable("titulo") String titulo) {
		try {
			return ResponseEntity.ok(tarefaService.buscarTarefaTitulo(titulo));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/tarefas-status")
	public Map<Boolean, Long> buscarTarefasStatus(){
		return tarefaService.buscarTarefasStatus();
	}
	
	@GetMapping("/tarefas-expirando/{dias}")
	public List<TarefaDto> buscarTarefasExpirando(@PathVariable("dias") Long dias){
		return tarefaService.buscarTarefasExpiramBreve(dias);
	}
	
	// PUT

	@PutMapping("/tarefas/{id}")
	public ResponseEntity<?> atualizarTarefa(@PathVariable("id") Long id, @RequestBody TarefaDto atualizacao) {
		try {
			return ResponseEntity.ok(tarefaService.atualizarTarefa(id, atualizacao));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// DELETE

	@DeleteMapping("/tarefas/{id}")
	public void deletarTarefa(@PathVariable("id") Long id) {
		tarefaService.deletarTarefa(id);
	}

}

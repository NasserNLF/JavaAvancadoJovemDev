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

import jv.triersistemas.primeiro_projeto.dto.TarefaDto;
import jv.triersistemas.primeiro_projeto.service.TarefaService;

@RestController
@RequestMapping("/tarefa-controller")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;

	@GetMapping("/tarefas")
	public List<TarefaDto> getAllTarefas() {
		return tarefaService.getAllTarefas();
	}

	@GetMapping("/tarefas/{id}")
	public ResponseEntity<?> getTarefa(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(tarefaService.getTarefa(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/tarefas")
	public TarefaDto postTarefa(@RequestBody TarefaDto tarefa) {
		return tarefaService.postTarefa(tarefa);
	}

	@PutMapping("tarefas/{id}")
	public ResponseEntity<?> putTarefa(@PathVariable("id") Long id, @RequestBody TarefaDto atualizacao) {
		try {
			return ResponseEntity.ok(tarefaService.putTarefa(id, atualizacao));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/tarefas/{id}")
	public void deleteTarefa(@PathVariable("id") Long id) {
		tarefaService.deleteTarefa(id);
	}

}

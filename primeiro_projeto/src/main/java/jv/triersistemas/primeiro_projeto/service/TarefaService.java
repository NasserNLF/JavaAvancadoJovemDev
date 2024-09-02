package jv.triersistemas.primeiro_projeto.service;

import java.util.List;

import jv.triersistemas.primeiro_projeto.dto.TarefaDto;

public interface TarefaService {
	
	List<TarefaDto> getAllTarefas();
	TarefaDto getTarefa(Long id);
	TarefaDto postTarefa(TarefaDto tarefa);
	TarefaDto putTarefa(Long id,TarefaDto tarefa);
	void deleteTarefa(Long id);
	TarefaDto getTarefasIncompletas();
}
	
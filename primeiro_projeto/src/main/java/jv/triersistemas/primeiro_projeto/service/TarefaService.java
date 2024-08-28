package jv.triersistemas.primeiro_projeto.service;

import java.util.List;

import jv.triersistemas.primeiro_projeto.dto.TarefaDto;

public interface TarefaService {
	
	List<TarefaDto> getTarefas();
	TarefaDto getTarefaEspecifica(Long id);
	TarefaDto postTarefa(TarefaDto tarefa);
	TarefaDto putTarefa(Long id,TarefaDto tarefa);
	void deleteTarefa(Long id);
}

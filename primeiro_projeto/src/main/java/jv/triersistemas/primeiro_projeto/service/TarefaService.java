package jv.triersistemas.primeiro_projeto.service;

import java.util.List;
import java.util.Map;

import jv.triersistemas.primeiro_projeto.dto.TarefaDto;

public interface TarefaService {
	
	List<TarefaDto> buscarTodasTarefas();
	TarefaDto buscarTarefa(Long id);
	TarefaDto salvarTarefa(TarefaDto tarefa);
	TarefaDto atualizarTarefa(Long id,TarefaDto tarefa);
	void deletarTarefa(Long id);
	
	List<TarefaDto> buscarTarefasIncompletas();
	List<TarefaDto> buscarTarefaTitulo(String titulo);
	List<TarefaDto> buscarTarefasExpiramBreve(Long dias);
	Map<Boolean, Long> buscarTarefasStatus();
	
	
}
	
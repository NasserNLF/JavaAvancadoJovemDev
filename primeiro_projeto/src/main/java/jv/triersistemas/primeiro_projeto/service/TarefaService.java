package jv.triersistemas.primeiro_projeto.service;

import java.util.List;
import java.util.Optional;

import jv.triersistemas.primeiro_projeto.dto.TarefaDto;
import jv.triersistemas.primeiro_projeto.entity.CategoriaEntity;
import jv.triersistemas.primeiro_projeto.entity.TarefaEntity;

public interface TarefaService {
	
	List<TarefaDto> getAllTarefas();
	TarefaDto getTarefa(Long id);
	TarefaDto postTarefa(TarefaDto tarefa);
	TarefaDto putTarefa(Long id,TarefaDto tarefa);
	void deleteTarefa(Long id);
	Optional<TarefaEntity> retornaBancoTarefa(Long id);
	CategoriaEntity verificaExistenciaCategoria(Long id);
}
	
package jv.triersistemas.primeiro_projeto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import jv.triersistemas.primeiro_projeto.dto.TarefaDto;
import jv.triersistemas.primeiro_projeto.entity.TarefaEntity;
import jv.triersistemas.primeiro_projeto.repository.TarefaRepository;
import jv.triersistemas.primeiro_projeto.service.TarefaService;

@Primary
@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	private TarefaRepository repository;

	@Override
	public List<TarefaDto> getAllTarefas() {
		return repository.findAll().stream().map(TarefaDto::new).toList();
	}

	@Override
	public TarefaDto getTarefa(Long id) {
		//Chamando método para verificar existência no banco
		return new TarefaDto(retornaBanco(id));
	}

	@Override
	public TarefaDto postTarefa(TarefaDto tarefa) {
		
		var tarefaEntity = new TarefaEntity(tarefa);
		
		// Entidade persistida -> Depois de voltar do BD
		TarefaEntity entidadePersistida = repository.save(tarefaEntity);

		return new TarefaDto(entidadePersistida);
	}

	@Override
	public TarefaDto putTarefa(Long id, TarefaDto atualizacao) {
		
		//Chamando método para verificar existência no banco
		var tarefaDto = new TarefaDto(retornaBanco(id));
		
		tarefaDto.atualizaRegistro(atualizacao);
		
		repository.save(new TarefaEntity(tarefaDto));
		return tarefaDto;

	}

	@Override
	public void deleteTarefa(Long id) {
		repository.deleteById(id);
	}
	
	//Verificação se o registro existe no banco
	@Override
	public TarefaEntity retornaBanco(Long id) {
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Não existe nenhum registro com esse ID"));
	}
}

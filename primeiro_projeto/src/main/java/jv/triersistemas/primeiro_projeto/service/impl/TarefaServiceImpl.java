package jv.triersistemas.primeiro_projeto.service.impl;

import java.util.List;
import java.util.Optional;

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
		// Chamando método para verificar existência no banco
		return (retornaBanco(id).isPresent()) ? new TarefaDto(retornaBanco(id).get()) : null;
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

		// Chamando método para verificar existência no banco
		Optional<TarefaEntity> tarefaEntity = retornaBanco(id);

		if (tarefaEntity.isPresent()) {
			
			var tarefaDto = new TarefaDto(tarefaEntity.get());
			
			tarefaDto.atualizaRegistro(atualizacao);

			repository.save(new TarefaEntity(tarefaDto));

			return tarefaDto;
		}

		return null;

	}

	@Override
	public void deleteTarefa(Long id) {
		repository.deleteById(id);
	}

	// Verificação se o registro existe no banco
	@Override
	public Optional<TarefaEntity> retornaBanco(Long id) {
		return repository.findById(id);
	}
}

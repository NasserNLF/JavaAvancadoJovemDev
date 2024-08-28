package jv.triersistemas.primeiro_projeto.service.impl;

import java.util.ArrayList;
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
	public List<TarefaDto> getTarefas() {
		return repository.findAll().stream().map(TarefaDto::new).toList();
	}

	@Override
	public TarefaDto getTarefaEspecifica(Long id) {
		var resultado = retornaBanco(id);
		return new TarefaDto(resultado);
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
		
		var entidadeEspecializada = retornaBanco(id);

		if (atualizacao.getTitulo() != null) {
			entidadeEspecializada.setTitulo(atualizacao.getTitulo());
		}
		if (atualizacao.getDescricao() != null) {
			entidadeEspecializada.setDescricao(atualizacao.getDescricao());
		}
		if (atualizacao.getCompleta() != null) {
			entidadeEspecializada.setCompleta(atualizacao.getCompleta());
		}

		repository.save(entidadeEspecializada);
		return new TarefaDto(entidadeEspecializada);

	}

	@Override
	public void deleteTarefa(Long id) {
		repository.deleteById(id);
	}
	
	@Override
	public TarefaEntity retornaBanco(Long id) {
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Não existe nenhum registro com esse ID"));
	}
}

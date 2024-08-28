package jv.triersistemas.primeiro_projeto.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import jv.triersistemas.primeiro_projeto.dto.TarefaDto;
import jv.triersistemas.primeiro_projeto.service.TarefaService;

@Primary
@Service
public class TarefaServiceImpl implements TarefaService {

	private static List<TarefaDto> tarefas = new ArrayList<>();
	private Long contador = 0L;

	@Override
	public List<TarefaDto> getTarefas() {
		return tarefas;
	}

	@Override
	public TarefaDto getTarefaEspecifica(Long id) {
		return tarefas.stream().filter(t -> t.getId().equals(id)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Tarefa Inexistente"));
	}

	@Override
	public TarefaDto postTarefa(TarefaDto tarefa) {
		tarefa.setId(++contador);
		tarefas.add(tarefa);

		return tarefa;
	}

	@Override
	public TarefaDto putTarefa(Long id, TarefaDto atualizacao) {
		TarefaDto tarefa = getTarefaEspecifica(id);

		if (atualizacao.getTitulo() != null) {
			tarefa.setTitulo(atualizacao.getTitulo());
		}
		if (atualizacao.getDescricao() != null) {
			tarefa.setDescricao(atualizacao.getDescricao());
		}
		if (atualizacao.getCompleta() != null) {
			tarefa.setCompleta(atualizacao.getCompleta());
		}

		return tarefa;
	}

	@Override
	public void deleteTarefa(Long id) {
		tarefas.removeIf(t -> t.getId().equals(id));
	}
}

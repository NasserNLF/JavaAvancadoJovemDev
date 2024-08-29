package jv.triersistemas.primeiro_projeto.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import jv.triersistemas.primeiro_projeto.dto.TarefaDto;
import jv.triersistemas.primeiro_projeto.entity.CategoriaEntity;
import jv.triersistemas.primeiro_projeto.entity.TarefaEntity;
import jv.triersistemas.primeiro_projeto.repository.TarefaRepository;
import jv.triersistemas.primeiro_projeto.service.CategoriaService;
import jv.triersistemas.primeiro_projeto.service.TarefaService;

@Primary
@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private CategoriaService categoriaService;

	@Override
	public List<TarefaDto> getAllTarefas() {
		List<TarefaEntity> tarefaEntities = tarefaRepository.findAll();
		List<TarefaDto> tarefaDtos = new ArrayList<TarefaDto>();
		
		tarefaEntities.forEach(te -> tarefaDtos.add(new TarefaDto(te)));
		
		return 
		
	}

	@Override
	public TarefaDto getTarefa(Long id) {

		// Chamando método para verificar existência no banco
		var tarefaOpcional = retornaBanco(id);

		return tarefaOpcional.map(TarefaDto::new).orElse(null);
	}

	@Override
	public TarefaDto postTarefa(TarefaDto tarefa) {

		var categoriaOptional = retornaBancoCategoria(tarefa.getCategoriaId());

		if (categoriaOptional.isPresent()) {
			// Entidade persistida -> Depois de voltar do BD

			var tarefaEntity = new TarefaEntity(tarefa, categoriaOptional.get());

			TarefaEntity entidadePersistida = tarefaRepository.save(tarefaEntity);

			return new TarefaDto(entidadePersistida);
		}

		return null;

	}

	@Override
	public TarefaDto putTarefa(Long id, TarefaDto atualizacao) {

		// Chamando método para verificar existência no banco
		Optional<TarefaEntity> tarefaEntity = retornaBanco(id);
		Optional<CategoriaEntity> categoriaEntity = retornaBancoCategoria(atualizacao.getCategoriaId());

		if (tarefaEntity.isPresent() && categoriaEntity.isPresent()) {

			tarefaRepository.save(tarefaEntity.get().atualizaRegistro(atualizacao, categoriaEntity.get()));

			return new TarefaDto(tarefaEntity.get());
		}

		return null;

	}

	@Override
	public void deleteTarefa(Long id) {
		tarefaRepository.deleteById(id);
	}

	// Verificação se o registro existe no banco
	@Override
	public Optional<TarefaEntity> retornaBanco(Long id) {
		return tarefaRepository.findById(id);
	}

	@Override
	public Optional<CategoriaEntity> retornaBancoCategoria(Long id) {
		return categoriaService.buscaIdBanco(id);
	}

}

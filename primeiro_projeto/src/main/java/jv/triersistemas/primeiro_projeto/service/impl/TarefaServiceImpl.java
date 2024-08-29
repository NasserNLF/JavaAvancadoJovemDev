package jv.triersistemas.primeiro_projeto.service.impl;

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
public class TarefaServiceImpl  implements TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private CategoriaService categoriaService;

	@Override
	public List<TarefaDto> getAllTarefas() {
		return tarefaRepository.findAll().stream().map(TarefaDto::new).toList();
	}

	@Override
	public TarefaDto getTarefa(Long id) {

		// Chamando método para verificar existência no banco
		var tarefaOpcional = retornaBancoTarefa(id);

		return tarefaOpcional.map(TarefaDto::new).orElse(null);
	}

	@Override
	public TarefaDto postTarefa(TarefaDto tarefa) throws RuntimeException {

		var categoriaOptional = verificaExistenciaCategoria(tarefa.getCategoriaId());

		// Entidade persistida -> Depois de voltar do BD
		var tarefaEntity = new TarefaEntity(tarefa, categoriaOptional);

		TarefaEntity entidadePersistida = tarefaRepository.save(tarefaEntity);

		return new TarefaDto(entidadePersistida);

	}

	@Override
	public TarefaDto putTarefa(Long id, TarefaDto atualizacao) throws RuntimeException {

		// Chamando método para verificar existência no banco
		Optional<TarefaEntity> tarefaEntity = retornaBancoTarefa(id);
		
		CategoriaEntity categoriaEntity = verificaExistenciaCategoria(atualizacao.getCategoriaId());

		if (tarefaEntity.isPresent()) {

			tarefaRepository.save(tarefaEntity.get().atualizaRegistro(atualizacao, categoriaEntity));

			return new TarefaDto(tarefaEntity.get());
		}
		return null;
	}

	@Override
	public void deleteTarefa(Long id){
		tarefaRepository.deleteById(id);
	}

	// Verificação se o registro existe no banco
	@Override
	public Optional<TarefaEntity> retornaBancoTarefa(Long id) {
		return tarefaRepository.findById(id);
	}

	@Override
	public CategoriaEntity verificaExistenciaCategoria(Long id) throws RuntimeException {
		return categoriaService.buscaIdBanco(id).orElseThrow(() -> new IllegalArgumentException("ERRO: A categoria não existe. Escolha uma categoria válida"));
		
	}


}

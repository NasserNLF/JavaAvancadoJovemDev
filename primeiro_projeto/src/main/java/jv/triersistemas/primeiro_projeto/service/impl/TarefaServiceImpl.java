package jv.triersistemas.primeiro_projeto.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// POST

	@Override
	public TarefaDto salvarTarefa(TarefaDto tarefa) {

		verificaDatas(tarefa.getDataInicio(), tarefa.getDataFim());

		var categoria = categoriaService.buscaIdBanco(tarefa.getCategoriaId());

		// Entidade persistida -> Depois de voltar do BD
		var tarefaEntity = new TarefaEntity(tarefa, categoria);

		return new TarefaDto(tarefaRepository.save(tarefaEntity));

	}

	// GET

	@Override
	public List<TarefaDto> buscarTodasTarefas() {
		return tarefaRepository.findAll().stream().map(TarefaDto::new).toList();
	}

	@Override
	public TarefaDto buscarTarefa(Long id) {
		return new TarefaDto(retornaBancoTarefa(id));
	}

	@Override
	public List<TarefaDto> buscarTarefasIncompletas(Long id) {
		
//		var categoria = categoriaService.buscaIdBanco(id);
		
		//TODO Fazer verificação para buscar por categoria
		
//		if () {
//			
//		}
		
//		return tarefaRepository.findAllByCategoria(categoria.get()).stream().filter(t -> t.getCompleta() == false)
//				.map(TarefaDto::new).toList();
		
		return null;
	}

	@Override
	public List<TarefaDto> buscarTarefaTitulo(String titulo) {
		var tarefasEntity = tarefaRepository.findAllByTitulo(titulo);

		if (tarefasEntity.isEmpty()) {
			throw new IllegalArgumentException("Nenhuma tarefa existe com esse título");
		}

		return tarefasEntity.stream().map(TarefaDto::new).toList();
	}

	@Override
	public List<TarefaDto> buscarTarefasExpiramBreve(Long dias) {
		return buscarTodasTarefas().stream().filter(t -> t.getDataFim().isBefore(LocalDate.now().plusDays(dias)))
				.toList();
	}

	@Override
	public Map<Boolean, Long> buscarTarefasStatus() {
		Map<Boolean, Long> mapa = new HashMap<>();

		mapa.put(true, tarefaRepository.countByCompleta(true));
		mapa.put(false, tarefaRepository.countByCompleta(false));

		return mapa;
	}

	// PUT

	@Override
	public TarefaDto atualizarTarefa(Long id, TarefaDto atualizacao) {

		// Chamando método para verificar existência no banco
		var tarefaEntity = retornaBancoTarefa(id);

		CategoriaEntity categoriaEntity = categoriaService.buscaIdBanco(atualizacao.getCategoriaId());

		return new TarefaDto(tarefaRepository.save(tarefaEntity.atualizaRegistro(atualizacao, categoriaEntity)));

	}

	// DELETE

	@Override
	public void deletarTarefa(Long id) {
		tarefaRepository.deleteById(id);
	}

	/*
	 * Validações
	 */

	public TarefaEntity retornaBancoTarefa(Long id) {
		return tarefaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ERRO: A tarefa não existe nos registros"));
	}

	public void verificaDatas(LocalDate dataCriacao, LocalDate dataFim) {
		if (!dataCriacao.isEqual(LocalDate.now())) {
			throw new IllegalArgumentException("ERRO: A data de criação deve ser igual a HOJE");
		}
		if (!dataFim.isAfter(dataCriacao)) {
			throw new IllegalArgumentException("ERRO: A data de expiração deve ser após a data de criação");
		}
	}

	

	

	

}

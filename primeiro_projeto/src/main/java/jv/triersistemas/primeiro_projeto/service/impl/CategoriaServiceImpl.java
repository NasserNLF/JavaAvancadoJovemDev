package jv.triersistemas.primeiro_projeto.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import jv.triersistemas.primeiro_projeto.dto.CategoriaDto;
import jv.triersistemas.primeiro_projeto.entity.CategoriaEntity;
import jv.triersistemas.primeiro_projeto.repository.CategoriaRepository;
import jv.triersistemas.primeiro_projeto.service.CategoriaService;

@Primary
@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//POST
	
	@Override
	public CategoriaDto salvarCategoria(CategoriaDto categoriaDto) {

		var categoriaEntity = new CategoriaEntity(categoriaDto);
		categoriaRepository.save(categoriaEntity);

		return new CategoriaDto(categoriaEntity);
	}
	
	
	//GET

	@Override
	public List<CategoriaDto> buscarTodasCategoria() {
		return categoriaRepository.findAll().stream().map(CategoriaDto::new).toList();
	}

	@Override
	public CategoriaDto buscarCategoria(Long id) {

		var categoriaEntity = buscaIdBanco(id);

		return (categoriaEntity.isPresent()) ? new CategoriaDto(categoriaEntity.get()) : null;
	}
	
	@Override
	public Map<CategoriaDto, Integer> buscarContagemTarefaCategoria() {
		
		Map<CategoriaDto, Integer> mapa = new HashMap<>();
		
		categoriaRepository.findAll().forEach(c -> mapa.put(new CategoriaDto(c), c.getTarefas().size()));
		
		return mapa;
	}
	
	//PUT
	
	@Override
	public CategoriaDto atualizarCategoria(Long id, CategoriaDto categoriaDto) throws RuntimeException {

		var categoriaEntityOptional = buscaIdBanco(id);

		if (categoriaEntityOptional.isPresent()) {

			var categoriaEntity = categoriaEntityOptional.get().atualizaRegistro(categoriaDto);

			categoriaRepository.save(categoriaEntity);

			return new CategoriaDto(categoriaEntity);
		}

		return null;
	}
	
	
	
	//DELETE

	@Override
	public void deletarCategoria(Long id) throws RuntimeException {
		var categoriaEntity = categoriaRepository.findById(id);

		if (categoriaEntity.isPresent()) {
			if (categoriaEntity.get().getTarefas().isEmpty()) {
				categoriaRepository.deleteById(id);
			}
			throw new IllegalArgumentException(
					"ERRO: Delete primeiro as tarefas que possuem essa categoria antes de exclui-lá");
		}

	}
	
	/*
	 * VALIDAÇÕES
	 */

	@Override
	public Optional<CategoriaEntity> buscaIdBanco(Long id) {
		return categoriaRepository.findById(id);
	}



}

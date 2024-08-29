package jv.triersistemas.primeiro_projeto.service.impl;

import jv.triersistemas.primeiro_projeto.dto.CategoriaDto;
import jv.triersistemas.primeiro_projeto.entity.CategoriaEntity;
import jv.triersistemas.primeiro_projeto.repository.CategoriaRepository;
import jv.triersistemas.primeiro_projeto.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public CategoriaDto salvarCategoria(CategoriaDto categoriaDto) {

		var categoriaEntity = new CategoriaEntity(categoriaDto);
		categoriaRepository.save(categoriaEntity);

		return new CategoriaDto(categoriaEntity);
	}

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
	public CategoriaDto atualizarCategoria(Long id, CategoriaDto categoriaDto) {

		var categoriaEntity = buscaIdBanco(id);

		if (categoriaEntity.isPresent()) {
			categoriaRepository.save(categoriaEntity.get().atualizaRegistro(categoriaDto));

			return new CategoriaDto(categoriaEntity.get());
		}

		return null;
	}

	@Override
	public void deletarCategoria(Long id) {
		categoriaRepository.deleteById(id);

	}

	@Override
	public Optional<CategoriaEntity> buscaIdBanco(Long id) {
		return categoriaRepository.findById(id);
	}
}

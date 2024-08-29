package jv.triersistemas.primeiro_projeto.service;

import java.util.List;
import java.util.Optional;

import jv.triersistemas.primeiro_projeto.dto.CategoriaDto;
import jv.triersistemas.primeiro_projeto.entity.CategoriaEntity;

public interface CategoriaService {
	CategoriaDto salvarCategoria(CategoriaDto categoriaDto);
	List<CategoriaDto> buscarTodasCategoria();
	CategoriaDto buscarCategoria(Long id);
	CategoriaDto atualizarCategoria(Long id, CategoriaDto categoriaDto);
	void deletarCategoria(Long id);
	Optional<CategoriaEntity> buscaIdBanco(Long id);
}

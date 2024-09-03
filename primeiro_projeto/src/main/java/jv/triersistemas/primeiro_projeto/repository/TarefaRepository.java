package jv.triersistemas.primeiro_projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jv.triersistemas.primeiro_projeto.dto.CategoriaDto;
import jv.triersistemas.primeiro_projeto.entity.CategoriaEntity;
import jv.triersistemas.primeiro_projeto.entity.TarefaEntity;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {
	List<TarefaEntity> findAllByTitulo(String titulo);
	Long countByCompleta(Boolean completa);
	List<TarefaEntity> findAllByCategoria(CategoriaEntity categoriaEntity);
}

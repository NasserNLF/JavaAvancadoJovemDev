package jv.triersistemas.primeiro_projeto.repository;

import jv.triersistemas.primeiro_projeto.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

}





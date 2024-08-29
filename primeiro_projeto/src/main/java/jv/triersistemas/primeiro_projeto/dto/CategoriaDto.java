package jv.triersistemas.primeiro_projeto.dto;

import jv.triersistemas.primeiro_projeto.entity.CategoriaEntity;
import jv.triersistemas.primeiro_projeto.enums.PrioridadeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoriaDto {
    private Long id;

    private String nome;
    private String descricao;
    private PrioridadeEnum prioridade;

    public CategoriaDto(CategoriaEntity categoriaEntity) {
        this.id = categoriaEntity.getId();
        this.nome = categoriaEntity.getNome();
        this.descricao = categoriaEntity.getDescricao();
        this.prioridade = categoriaEntity.getPrioridade();
    }
}

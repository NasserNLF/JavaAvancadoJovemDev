package jv.triersistemas.primeiro_projeto.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jv.triersistemas.primeiro_projeto.dto.CategoriaDto;
import jv.triersistemas.primeiro_projeto.enums.PrioridadeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "categoria")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String descricao;

    @Enumerated(EnumType.ORDINAL)
    private PrioridadeEnum prioridade;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<TarefaEntity> tarefas;

    public CategoriaEntity(CategoriaDto categoriaDto) {
        this.id = categoriaDto.getId();
        this.nome = categoriaDto.getNome();
        this.descricao = categoriaDto.getDescricao();
        this.prioridade = categoriaDto.getPrioridade();
    }
    
    public CategoriaEntity atualizaRegistro(CategoriaDto categoriaDto) {
         this.nome = categoriaDto.getNome();
         this.descricao = categoriaDto.getDescricao();
         this.prioridade = categoriaDto.getPrioridade();
         
         return this;
    }

}

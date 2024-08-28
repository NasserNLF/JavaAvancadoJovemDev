package jv.triersistemas.primeiro_projeto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jv.triersistemas.primeiro_projeto.dto.TarefaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tarefa")
public class TarefaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String titulo;
	private String descricao;
	private Boolean completa;
	
	public TarefaEntity(TarefaDto dto) {
		this.id = dto.getId();
		this.titulo = dto.getTitulo();
		this.descricao = dto.getDescricao();
		this.completa = dto.getCompleta();
	}
}

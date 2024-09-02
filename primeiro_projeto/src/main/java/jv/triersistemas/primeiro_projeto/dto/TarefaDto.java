package jv.triersistemas.primeiro_projeto.dto;

import java.time.LocalDate;

import jv.triersistemas.primeiro_projeto.entity.TarefaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TarefaDto {
	private Long id;
	private String titulo;
	private String descricao;
	private Boolean completa;
	private Long categoriaId;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	
	public TarefaDto(TarefaEntity entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.descricao = entity.getDescricao();
		this.completa = entity.getCompleta();
		this.categoriaId = entity.getCategoria().getId();
		this.dataInicio = entity.getDataInicio();
		this.dataFim = entity.getDataFim();
	}
	
//	public TarefaDto atualizaRegistro(TarefaDto atualizacao) {
//		this.titulo = atualizacao.getTitulo();
//		this.descricao = atualizacao.getDescricao();
//		this.completa = atualizacao.getCompleta();
//		
//		return this;
//	}
	
}

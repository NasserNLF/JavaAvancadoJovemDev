package desafio_dados.desafio_dados.service;

import desafio_dados.desafio_dados.dto.ApostaDto;

public interface ApostaService {
	
	Boolean verificaCondicaoNumDados(Integer numDados);
	Boolean verificaCondicaoApostaDados(Integer numDados, Integer valorAposta);
	String postValorAposta(ApostaDto aposta);
	Double porcentagemAposta(Integer valorSoma, Integer valorTotal);
	
	
	
}

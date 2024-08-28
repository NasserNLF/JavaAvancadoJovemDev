package desafio_dados.desafio_dados.service.impl;

import java.util.Random;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import desafio_dados.desafio_dados.dto.ApostaDto;
import desafio_dados.desafio_dados.service.ApostaService;

@Primary
@Service
public class ApostaServiceImpl implements ApostaService {

	
	Random r = new Random();

	@Override
	public Boolean verificaCondicaoNumDados(Integer numDados) {
		return (numDados > 0 && numDados < 5);
	}

	@Override
	public Boolean verificaCondicaoApostaDados(Integer numDados, Integer valorAposta) {
		return (numDados * 6 >= valorAposta);
	}

	@Override
	public String postValorAposta(ApostaDto aposta) {
		
		StringBuilder sb = new StringBuilder();
		
		if (!verificaCondicaoNumDados(aposta.getQtdDados())) {
			return "ERRO: NÚMERO DE DADOS MÍNIMO: 1, MÁXIMO: 4";
		}
		if (!verificaCondicaoApostaDados(aposta.getQtdDados(), aposta.getValorTotal())) {
			return "ERRO: NÚMERO DA APOSTA EXCEDE O VALOR QUE A SOMA DO(S) DADO(S) PODE(M) CHEGAR";
		}

		Integer soma = 0;

		for (int i = 1; i <= aposta.getQtdDados(); i++) {
			Integer valorSorteado = r.nextInt(6) + 1;
			sb.append("Dado " + i + " valor sorteado: " + valorSorteado + ",");
			soma += valorSorteado;
		}
		sb.append("\nPercentual em relação ao sorteio: " + String.format("%.2f", porcentagemAposta(soma, aposta.getValorTotal()))  + "\n");

		return sb.toString();
	}

	@Override
	public Double porcentagemAposta(Integer valorSoma, Integer valorAposta) {
		if (valorSoma > valorAposta) {
			return ((double) valorAposta / valorSoma) * 100;
		}
		
		return ((double) valorSoma / valorAposta) * 100;
	}

}

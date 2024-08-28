package desafio_dados.desafio_dados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio_dados.desafio_dados.dto.ApostaDto;
import desafio_dados.desafio_dados.service.ApostaService;

@RestController
@RequestMapping("/aposta")
public class ApostaController {
	
	@Autowired
	ApostaService apostaService;
	
	@PostMapping
	public String postAposta(@RequestBody ApostaDto aposta) {
		return apostaService.postValorAposta(aposta);
	}
	
}

package jv.triersistemas.primeiro_projeto.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/primeiro-controller")
public class PrimeiroController {
	
	private static List<Produto> produtos = new ArrayList<>();
	
	@GetMapping("/texto")
	public String getText() {
		return "Hello World!";
	}
	
	@GetMapping("/produto")
	public Produto getProduto() {
		return new Produto("Rivotril", BigDecimal.valueOf(15.10));
	}
	
	@GetMapping("/get-all")
	public List<Produto> getAll(){
		return produtos;
	}
	
	@PostMapping
	public void cadastraProduto(@RequestBody Produto produto){
		produtos.add(produto);
	}
	
	//teste
	
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
class Produto{
	
	private String nome;
	private BigDecimal preco;

}

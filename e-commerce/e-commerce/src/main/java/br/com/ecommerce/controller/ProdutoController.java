package br.com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.model.Produto;
import br.com.ecommerce.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nomeProduto}")
	public ResponseEntity<List<Produto>> GetbyTitulo(@PathVariable String nomeProduto){
		return ResponseEntity.ok(repository.findAllByNomeProdutoContainingIgnoreCase(nomeProduto));
	}
	
	@GetMapping("/precoMaior/{preco}")
	public ResponseEntity<List<Produto>> GetAllByPrecoMaior(@PathVariable int preco){
		return ResponseEntity.ok(repository.findAllByPrecoGreaterThanEqual(preco));
	}
	
	@GetMapping("/precoMenor/{preco}")
	public ResponseEntity<List<Produto>> GetAllByPrecoLess(@PathVariable int preco){
		return ResponseEntity.ok(repository.findAllByPrecoLessThanEqual(preco));
	}
	
	@GetMapping("/ativo/{ativo}")
	public ResponseEntity<List<Produto>> GetAllByAtivo(@PathVariable boolean ativo){
		return ResponseEntity.ok(repository.findAllByAtivo(ativo));
	}
	
	@GetMapping("/marca/{marca}")
	public ResponseEntity<List<Produto>> GetAllProdutoByCategoriaDecicao(@PathVariable String marca){
		return ResponseEntity.ok(repository.findAllProdutoByCategoriaDecicao(marca));
	}
	
	@PostMapping
	public ResponseEntity<Produto> post (@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> put (@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id){
		repository.deleteById(id);
	}
}

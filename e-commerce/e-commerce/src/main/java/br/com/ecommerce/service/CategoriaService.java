package br.com.ecommerce.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ecommerce.model.Categoria;
import br.com.ecommerce.model.Produto;
import br.com.ecommerce.repository.CategoriaRepository;
import br.com.ecommerce.repository.ProdutoRepository;

@Service
public class CategoriaService 
{
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria cadastroProdutoCategoria(long categoriaId, long produtoId) 
	{
		Optional<Produto> produtoExistente = produtoRepository.findById(produtoId);
		Optional<Categoria> categoriaExistente = categoriaRepository.findById(categoriaId);
	
		if (produtoExistente.isPresent()&&categoriaExistente.isPresent()) {
			
			categoriaExistente.get().getProdutos().add(produtoExistente.get());
			
			categoriaRepository.save(categoriaExistente.get());
			
			return categoriaRepository.save(categoriaExistente.get());
		}
		return null;
	}
}
package br.com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.model.Categoria;
import br.com.ecommerce.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> 
{
	public List<Categoria> findAllByDescricaoContainingIgnoreCase (String descricao); 
}
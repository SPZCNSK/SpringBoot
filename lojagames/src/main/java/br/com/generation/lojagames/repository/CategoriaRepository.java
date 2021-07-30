package br.com.generation.lojagames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.generation.lojagames.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
//NÃO SE IMPLEMENTA CÓDIGO EM INTERFACE, ASSINA-SE MÉTODOS, OU SEJA, QUAIS PARAMETROS ESSES MÉTODOS TERÃO, SE SERÃO VOID, ETC...

	public List<Categoria> findAllByTipoContainingIgnoreCase(String tipo);
}

package br.com.generation.lojagames.controller;

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

import br.com.generation.lojagames.model.Categoria;
import br.com.generation.lojagames.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria") //endpoint sempre minusculo, pois é endereço web
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	//Injeção de dependência
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	private ResponseEntity<List<Categoria>> getAll() {		
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	@GetMapping("/{id}") //sempre que um parâmatro estiver dentro de chaves será uma variável
	private ResponseEntity<Categoria> getById(@PathVariable long id) {
		
		return categoriaRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tipo/{tipo}")
	/*A API entende que depois de uma barra '/' o ultimo dado que será o PathVariable não entende o nome entre chaves
	 *é preciso ter cuidado com duplicidade de endpoint, do método acima */
	public ResponseEntity<List<Categoria>> GetByTitulo(@PathVariable String tipo){
		return ResponseEntity.ok(categoriaRepository.findAllByTipoContainingIgnoreCase(tipo));	
	}
	
	@PostMapping	
	public ResponseEntity<Categoria> post (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	}
	
	@PutMapping	
	public ResponseEntity<Categoria> put (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
	}
	
	@DeleteMapping("/{id}") //chaves são chamadas de interpolação ou template literals
	public void delete(@PathVariable long id) {
		categoriaRepository.deleteById(id);
	}

}

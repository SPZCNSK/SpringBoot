package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
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

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*") /*permite que servidores diferentes de back-end e front 
funcionem juntos mesmo não tendo a mesma origem*/

public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	/*Listar todas as postagens*/
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
				}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
		/*Assim que for feito alguma requisição do tipo get em 'postagens/' no atributo id, 
		 * 
		 * o método 'getById' vai capturar a variável (vinda pela url) recebida dentro da anotação PathVariable 
		 *  
		 * retorna-se então a interface injetada com AutoWired 'return repository.findById(id)'
		 * 
		 * o método findById devolverá TANTO um objeto postagem com ok e o objeto dentro do corpo da requisição
		 * 
		 *  QUANTO um notFound no método orElse caso o obj não exista ou caso exista um erro na requisição*/
	}
	
	
	@GetMapping("/titulo/{titulo}")
	/*A API entende que depois de uma barra '/' o ultimo dado que será o PathVariable não entende o nome entre chaves
	 *é preciso ter cuidado com duplicidade de endpoint, do método acima */
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));		
	}
	
	/*Aqui foi utilizado o metodo contido no repository, que traz todos os valores de acordo
	 * com o que é digitado sem distinção de maiusculo e minusculo */
	
	@PostMapping	
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping	
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}") //chaves são chamadas de interpolação ou template literals
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	

}

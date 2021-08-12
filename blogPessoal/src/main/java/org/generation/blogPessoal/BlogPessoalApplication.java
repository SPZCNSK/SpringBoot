package org.generation.blogPessoal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class BlogPessoalApplication {
	
	@GetMapping
	public ModelAndView swaggerUi() {
		return new ModelAndView("redirect:/swagger-ui/");
	}
	
	/* As alterações acima transformam a classe principal da nossa API (BlogpessoalApplication) 
	 * em uma classe do tipo RestController, que responderá à todas as requisições do tipo GET
	   para o endpoint “/” (raiz do nosso projeto) e fará o redirecionamento para o Swagger, ou seja, oSwagger será a página home do nosso projeto.*/

	public static void main(String[] args) {
		SpringApplication.run(BlogPessoalApplication.class, args);
	}

}

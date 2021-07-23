package br.com.generation.helloworld2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/helloObj")
public class HelloWObjetivos {
	
	@GetMapping
	public String objetivos() {
		return "Objetivos de aprendizado para a semana: Revisar o conteúdo de Banco de Dados e de Java"
				+ "\n\nFazendo exercício para fixar o aprendizado";
	}

}

package com.urbana.desafio;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "urbana simple app API", version = "1",description = "A API \"urbana simple app\" oferece um conjunto de recursos RESTful para gerenciar usuários e seus cartões de ônibus.", contact = @Contact( name = "Github", url = "https://github.com/fh3mrique/urbana-simple-app",email = "fhemrique55@gmail.com")))
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

}

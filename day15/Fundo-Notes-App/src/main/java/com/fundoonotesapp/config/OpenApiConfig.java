package com.fundoonotesapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {

		Server localServer = new Server();
		localServer.setUrl("http://localhost:8080");
		localServer.setDescription("Local Development Server");

		Contact contact = new Contact();
		contact.setName("Rudresh Sharma");
		contact.setEmail("rudresh.sharma.8602@gmail.com");

		License license = new License();
		license.setName("Apache 2.0");

		Info info = new Info().title("Fundo Notes APP  API's").version("1.0.0").description("""
				A note taking app backend supported on spring boot 4.1.0
				  """).contact(contact).license(license);

		return new OpenAPI().info(info).servers(List.of(localServer));
	}
}
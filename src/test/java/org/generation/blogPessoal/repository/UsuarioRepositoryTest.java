package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //precisou de uma anotação e dependência chamada Autoconfigure para BD

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
    
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start(){

		usuarioRepository.deleteAll();
		
		usuarioRepository.save(new Usuario(0L, "João da Silva","joao@email.com.br", "13465278"));
		
		usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "manuela@email.com.br", "13465278"));
		
		usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriana@email.com.br", "13465278"));

        usuarioRepository.save(new Usuario(0L, "Paulo Antunes", "paulo@email.com.br", "13465278"));

	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
	}
	
}

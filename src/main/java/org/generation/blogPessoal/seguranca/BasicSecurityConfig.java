package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		
		auth.inMemoryAuthentication().withUser("raiza").password(passwordEncoder().encode("raiza")).authorities("ADMIN");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {//tem que configurar a rota
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll()//rota liberada
		.antMatchers("/usuarios/cadastrar").permitAll()// rota liberada
		.anyRequest().authenticated()//quaisquers outras
		.and().httpBasic()//autenticação padrão basic
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//não gerencio o tempo que o usuário fica logado
		.and().cors()//permissão de acesso pra um cliente externo
		.and().csrf().disable();//desabilita o ataque csrf
	}

}

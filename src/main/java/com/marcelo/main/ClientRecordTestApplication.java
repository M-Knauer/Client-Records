package com.marcelo.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ClientRecordTestApplication implements CommandLineRunner {
	
	@Autowired
	PasswordEncoder pw;

	public static void main(String[] args) {
		SpringApplication.run(ClientRecordTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		long memoriaLivre = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("Memoria Livre:"+memoriaLivre);
		
		System.out.println("Senha: "+ pw.encode("123"));
		
	}


}

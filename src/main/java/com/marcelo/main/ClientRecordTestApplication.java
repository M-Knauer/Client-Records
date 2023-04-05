package com.marcelo.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientRecordTestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ClientRecordTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		long memoriaLivre = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("Memoria Livre:"+memoriaLivre);
		
	}


}

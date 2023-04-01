package com.marcelo.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marcelo.main.dto.ClientMinDTO;
import com.marcelo.main.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query("""
			SELECT new ClientMinDTO(c.name, c.cpf, c.income) 
			FROM Client c 
			WHERE c.cpf = :cpf
			""")
	ClientMinDTO buscarPorCpf(String cpf);
}

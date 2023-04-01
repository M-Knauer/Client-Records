package com.marcelo.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.main.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}

package com.marcelo.main.dto;

import java.time.LocalDate;

import com.marcelo.main.entities.Client;

public record ClientDTO(
		
		Long id,
		String name,
		String cpf,
		Double income,
		LocalDate birthDate,
		Integer children,
		EnderecoDTO endereco
		
		) {
	
	public static ClientDTO mapClient(Client entity) {
        ClientDTO dto = new ClientDTO(
        		entity.getId(), 
        		entity.getName(), 
                entity.getCpf(), 
                entity.getIncome(),
                entity.getBirthDate(),
                entity.getChildren(),
                EnderecoDTO.mapEndereco(entity.getEndereco()));
        return dto;
    }
}

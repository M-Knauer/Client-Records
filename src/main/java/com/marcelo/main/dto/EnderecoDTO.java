package com.marcelo.main.dto;

import com.marcelo.main.entities.Endereco;

public record EnderecoDTO(
		
		Long id,
		String cep,
		String logradouro,
		String bairro,
		String localidade,
		String uf
		
		) {
	
	public static EnderecoDTO mapEndereco(Endereco entity) {
        EnderecoDTO dto = new EnderecoDTO(
        		entity.getId(),
        		entity.getCep(),
        		entity.getLogradouro(),
        		entity.getBairro(),
        		entity.getLocalidade(),
        		entity.getUf()
        		);
        
        return dto;
    }
}

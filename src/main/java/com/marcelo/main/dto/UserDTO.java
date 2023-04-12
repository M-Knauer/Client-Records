package com.marcelo.main.dto;

import com.marcelo.main.entities.User;

public record UserDTO(
		Long id,
		String login,
		String password	
	) {

	public UserDTO(User entity) {
		this(
				entity.getId(),
				entity.getLogin(), 
				entity.getPassword()
			);
	}
}

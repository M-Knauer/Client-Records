package com.marcelo.main.dto;

import com.marcelo.main.entities.User;

public record UserDTO(
		Long id,
		String login,
		String password	
	) {

	public static UserDTO mapUser(User entity) {
		UserDTO dto = new UserDTO(
				entity.getId(),
				entity.getLogin(), 
				entity.getPassword());
		return dto;
	}
}

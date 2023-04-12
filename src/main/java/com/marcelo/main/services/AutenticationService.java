package com.marcelo.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcelo.main.dto.UserDTO;
import com.marcelo.main.entities.User;
import com.marcelo.main.repositories.UserRepository;

@Service
public class AutenticationService implements UserDetailsService{

	@Autowired
	UserRepository repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
	
		return repo.findByLogin(login);
	}

	@Transactional
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		entity.setLogin(dto.login());
		entity.setPassword(encoder.encode(dto.password()));
		return new UserDTO(repo.save(entity));
	}

}

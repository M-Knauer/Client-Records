package com.marcelo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.main.dto.TokenJwtDTO;
import com.marcelo.main.dto.UserDTO;
import com.marcelo.main.entities.User;
import com.marcelo.main.security.config.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticationController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> efetuarLogin(@RequestBody UserDTO dto) {
		var authToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
		var auth = manager.authenticate(authToken);
		var tokenJwt = tokenService.generateToken((User) auth.getPrincipal());
		return ResponseEntity.ok(new TokenJwtDTO(tokenJwt));
		
	}
}

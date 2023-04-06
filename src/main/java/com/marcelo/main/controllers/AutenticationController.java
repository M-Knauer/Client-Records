package com.marcelo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.main.dto.UserDTO;

@RestController
@RequestMapping("/login")
public class AutenticationController {

	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping
	public ResponseEntity<?> efetuarLogin(@RequestBody UserDTO dto) {
		var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
		var auth = manager.authenticate(token);
		return ResponseEntity.ok().build();
		
	}
}

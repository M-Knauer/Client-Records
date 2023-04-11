package com.marcelo.main.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcelo.main.dto.ClientDTO;
import com.marcelo.main.dto.ClientMinDTO;
import com.marcelo.main.services.ClientService;


@RestController
@RequestMapping("clients")
public class ClientController {

	@Autowired
	ClientService service;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok().body(service.findAll(pageable));
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<ClientMinDTO> findById(@PathVariable String cpf) {
		return ResponseEntity.ok().body(service.buscarPorCpf(cpf));
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@PostMapping()
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto) {
		dto = service.insert(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.id())
                .toUri();
        
		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dto) {
		return ResponseEntity.ok().body(service.update(id, dto));
	}
	
}
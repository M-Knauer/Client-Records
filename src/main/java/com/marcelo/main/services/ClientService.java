package com.marcelo.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.marcelo.main.dto.ClientDTO;
import com.marcelo.main.dto.ClientMinDTO;
import com.marcelo.main.dto.EnderecoDTO;
import com.marcelo.main.entities.Client;
import com.marcelo.main.entities.Endereco;
import com.marcelo.main.repositories.ClientRepository;
import com.marcelo.main.repositories.EnderecoRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Transactional(readOnly = true)
	public ClientMinDTO buscarPorCpf(String cpf) {
		
		return repository.buscarPorCpf(cpf);
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		
		return ClientDTO.mapClient(repository.findById(id).get());
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		
		toEntity(dto, entity);
				
		return ClientDTO.mapClient(repository.save(entity));
	}

	@Transactional(readOnly = true)
	public List<ClientDTO> findAll(Pageable pageable) {

		return repository.findAll(pageable).stream().map(x -> ClientDTO.mapClient(x)).toList();
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		Client entity = repository.getReferenceById(id);
		
		toEntity(dto, entity);
		
		return ClientDTO.mapClient(repository.save(entity));
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	private void toEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.name());
		entity.setCpf(dto.cpf());
		entity.setIncome(dto.income());
		entity.setBirthDate(dto.birthDate());
		entity.setChildren(dto.children());
		Endereco endereco = new Endereco();
		if (entity.getEndereco() == null) {
			endereco = new RestTemplate()
					.getForObject("https://viacep.com.br/ws/"+dto.endereco().cep() +"/json/", Endereco.class);
		}
		else {
			endereco = enderecoRepository.getReferenceById(entity.getEndereco().getId());
			EnderecoDTO endDto = EnderecoDTO.mapEndereco(
					new RestTemplate()
					.getForObject("https://viacep.com.br/ws/"+dto.endereco().cep() +"/json/", Endereco.class));
			endereco.setBairro(endDto.bairro());
			endereco.setCep(endDto.cep());
			endereco.setLocalidade(endDto.localidade());
			endereco.setLogradouro(endDto.logradouro());
			endereco.setUf(endDto.uf());
		
		}
		
		entity.setEndereco(endereco);
		
	}

}

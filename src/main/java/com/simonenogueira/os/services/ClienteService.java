package com.simonenogueira.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simonenogueira.os.domain.Pessoa;
import com.simonenogueira.os.domain.Cliente;
import com.simonenogueira.os.dtos.ClienteDTO;
import com.simonenogueira.os.repository.PessoaRepository;
import com.simonenogueira.os.repository.ClienteRepository;
import com.simonenogueira.os.services.exception.DataIntegratyViolationException;
import com.simonenogueira.os.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ",Tpo:" + Cliente.class.getName()));

	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		if (findByCpf(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");

		}
		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);

		if (findByCpf(objDTO) != null && findByCpf(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");

		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return repository.save(oldObj);

	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Pessoa possui Oredens de Serviço, não pode ser deletado!");
			
		}
		
		repository.deleteById(id);
		
	}

	private Pessoa findByCpf(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	

	
	
	}
}

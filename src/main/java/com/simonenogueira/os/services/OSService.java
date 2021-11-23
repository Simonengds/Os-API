package com.simonenogueira.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simonenogueira.os.domain.Cliente;
import com.simonenogueira.os.domain.OS;
import com.simonenogueira.os.domain.Tecnico;
import com.simonenogueira.os.domain.enuns.Prioridade;
import com.simonenogueira.os.domain.enuns.Status;
import com.simonenogueira.os.dtos.OSDTO;
import com.simonenogueira.os.repository.OSRepository;
import com.simonenogueira.os.services.exception.ObjectNotFoundException;

@Service
public class OSService {

	@Autowired
	private OSRepository repository;

	@Autowired
	private TecnicoService tencicoservice;

	@Autowired
	private ClienteService clienteservice;

	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id" + id + ", Tipo: " + OS.class.getName()));

	}

	public List<OS> findAll() {
		return repository.findAll();

	}

	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj);

	}

	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);

	}

	private OS fromDTO(OSDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade().getCod()));
		newObj.setStatus(Status.toEnum(obj.getStatus().getCod()));

		Tecnico tec = tencicoservice.findById(obj.getTecnico());
		Cliente cli = clienteservice.findById(obj.getCliente());

		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDateFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);

	}

}

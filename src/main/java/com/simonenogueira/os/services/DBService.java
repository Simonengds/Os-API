package com.simonenogueira.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simonenogueira.os.domain.Cliente;
import com.simonenogueira.os.domain.OS;
import com.simonenogueira.os.domain.Tecnico;
import com.simonenogueira.os.domain.enuns.Prioridade;
import com.simonenogueira.os.repository.ClienteRepository;
import com.simonenogueira.os.repository.OSRepository;
import com.simonenogueira.os.repository.TecnicoRepository;

import com.simonenogueira.os.domain.enuns.Status;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {

		Tecnico t1 = new Tecnico(null, "João Pedro", "054.740.654-18,", "(83) 98888-1380");

		Cliente c1 = new Cliente(null, "Ana Carolina", "690.521.154-00", "(83) 99999-1381");

		OS os1 = new OS(null, Prioridade.ALTA, "Teste creat OD", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);

		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));

	}

}

package com.simonenogueira.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simonenogueira.os.domain.OS;

public class OSDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy  HH:mm")

	private LocalDateTime dateAbertura;

	@JsonFormat(pattern = "dd/MM/yyyy  HH:mm")

	private LocalDateTime dateFechamento;
	private Integer prioridade;

	@NotEmpty(message = "O campo OBERSERVAÇÕES é requerido")
	private String observacoes;
	private Integer status;
	private Integer tecnico;
	private Integer cliente;

	public OSDTO() {
		super();

	}

	public OSDTO(OS obj) {
		super();
		this.id = obj.getId();
		this.dateAbertura = obj.getDateAbertura();
		this.dateFechamento = obj.getDateFechamento();
		this.prioridade = obj.getPrioridade().getCod();
		this.observacoes = obj.getObservacoes();
		this.status = obj.getStatus().getCod();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDateAbertura() {
		return dateAbertura;
	}

	public void setDateAbertura(LocalDateTime dateAbertura) {
		this.dateAbertura = dateAbertura;
	}

	public LocalDateTime getDateFechamento() {
		return dateFechamento;
	}

	public void setDateFechamento(LocalDateTime dateFechamento) {
		this.dateFechamento = dateFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

}

package com.clinoconect.clinica.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String medico;
	private LocalDateTime horario;
	private String informacoes;
	
}

package com.clinoconect.clinica.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	
}

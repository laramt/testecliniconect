package com.clinoconect.clinica.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.clinoconect.clinica.enums.Sexo;

import lombok.Data;

	@Data
	@Entity
	public class Paciente {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String nome;
		private Sexo sexo;
		@ManyToMany
		private List<Endereco> endereco;
		@Column(nullable = false, unique = true)
		private String cpf;
		@Column(nullable = false, unique = true)
		private String celular;
		private LocalDate dataDeNascimento;
		@Column(nullable = false, unique = true)
		private String email;
		@OneToMany
		@JoinTable(name = "paciente_consulta")
		private List<Consulta> consulta;
	
}

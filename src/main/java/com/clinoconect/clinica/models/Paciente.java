package com.clinoconect.clinica.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.clinoconect.clinica.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@Entity
	public class Paciente {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String nome;
		private Sexo sexo;
		@OneToMany
		@JoinTable(name = "paciente_endereco")
		@JsonIgnore
		private List<Endereco> endereco;
		@Column(nullable = false, unique = true)
		private String cpf;
		@Column(nullable = false)
		private String celular;
		@JsonFormat(pattern =  "dd/MM/yyyy")
		private LocalDate dataDeNascimento;
		@Column(nullable = false)
		private String email;
		@OneToMany
		@JoinTable(name = "paciente_consulta")
		@JsonIgnore
		private List<Consulta> consulta;
	
}

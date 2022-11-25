package com.clinoconect.clinica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.clinoconect.clinica.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	public List<Endereco> findAllByPacienteId(Long id);
	
}

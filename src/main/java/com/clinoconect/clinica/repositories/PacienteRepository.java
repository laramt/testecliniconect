package com.clinoconect.clinica.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinoconect.clinica.models.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	boolean existsByCpf(String cpf);

	boolean existsByEmail(String email);

	Page<Paciente> findByNome(String nome, Pageable pageable);

	Page<Paciente> findByCpf(String cpf, Pageable pageable);

	Page<Paciente> findByEmail(String email, Pageable pageable);

}

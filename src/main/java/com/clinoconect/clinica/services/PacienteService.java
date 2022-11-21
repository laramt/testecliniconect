package com.clinoconect.clinica.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clinoconect.clinica.models.Paciente;
import com.clinoconect.clinica.repositories.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	PacienteRepository pacienteRepository;

	@Transactional
	public Paciente save(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}

	@Transactional
	public void delete(Long id) {
		pacienteRepository.deleteById(id);
	}

	public Page<Paciente> findsAll(Pageable pageable) {
		return pacienteRepository.findAll(pageable);
	}

	public Optional<Paciente> findById(Long id) {
		return pacienteRepository.findById(id);
	}

	public Page<Paciente> findByNome(String nome, Pageable pageable) {
		return pacienteRepository.findByNome(nome, pageable);
	}

	public Page<Paciente> findByEmail(String email, Pageable pageable) {
		return pacienteRepository.findByEmail(email, pageable);
	}

	public Page<Paciente> findByCpf(String cpf, Pageable pageable) {
		return pacienteRepository.findByCpf(cpf, pageable);
	}

	public boolean existsByEmail(String email) {
		return pacienteRepository.existsByEmail(email);
	}

	public boolean existsByCpf(String cpf) {
		return pacienteRepository.existsByCpf(cpf);
	}

}

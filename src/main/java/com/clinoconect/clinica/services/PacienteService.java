package com.clinoconect.clinica.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clinoconect.clinica.models.Endereco;
import com.clinoconect.clinica.models.Paciente;
import com.clinoconect.clinica.repositories.EnderecoRepository;
import com.clinoconect.clinica.repositories.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	PacienteRepository pacienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Transactional
	public Paciente save(Paciente paciente) {
		
		// verifica email
		String email = paciente.getEmail();
		if(!pacienteRepository.existsByEmail(email)) {
			throw new RuntimeException("Email já existe.");
		}
		
		Pattern patternEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
		Matcher matcherEmail = patternEmail.matcher(email);
		if (!matcherEmail.matches()) {
			throw new RuntimeException("Formato invalido de email.");
		}

		// verifica cpf
		String cpf = paciente.getCpf();
		if(!pacienteRepository.existsByCpf(cpf)) {
			throw new RuntimeException("Cpf já existe.");
		}
		Pattern patternCpf = Pattern.compile("^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$");
		Matcher matcherCpf = patternCpf.matcher(cpf);
		if (!matcherCpf.matches()) {
			throw new RuntimeException("Formato invalido de cpf.");
		}

		// verifica data de nascimento
		String dataNascimento = paciente.getDataDeNascimento().toString();
		LocalDate date = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("uuuu-M-d"));
		if (date.isAfter(LocalDate.now().minusYears(1))) {
			throw new RuntimeException("Data de nascimento invalida");
		}

		return pacienteRepository.save(paciente);
	}
	
	public Paciente update(Long id, Paciente paciente) {
		Paciente pac = pacienteRepository.getById(id);
		
		pac.setNome(paciente.getNome());
		pac.setEmail(paciente.getEmail());
		pac.setSexo(paciente.getSexo());
		pac.setCpf(paciente.getCpf());
		pac.setCelular(paciente.getCelular());
		pac.setDataDeNascimento(paciente.getDataDeNascimento());
		
		return pacienteRepository.save(pac);
	}

	public Endereco insertEndereco(Endereco endereco, Long pacienteId) {
		Paciente paciente = pacienteRepository.getById(pacienteId);
		endereco.setPaciente(paciente);
		paciente.getEndereco().add(endereco);

		return enderecoRepository.save(endereco);
	}

	public List<Endereco> findAllEnderecosById(Long pacienteId) {
		List<Endereco> list = enderecoRepository.findAllByPacienteId(pacienteId);
		return list;
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

}

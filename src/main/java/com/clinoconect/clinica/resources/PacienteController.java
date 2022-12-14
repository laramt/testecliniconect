package com.clinoconect.clinica.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clinoconect.clinica.exeptions.ResourceNotFoundException;
import com.clinoconect.clinica.models.Endereco;
import com.clinoconect.clinica.models.Paciente;
import com.clinoconect.clinica.services.PacienteService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class PacienteController {

	@Autowired
	PacienteService pacienteService;

	@PostMapping("/novo-paciente")
	public ResponseEntity<Object> save(@RequestBody Paciente paciente) {

		// verifica campos nulos
		if(paciente.getCpf() == null || paciente.getEmail() == null) 
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Esse campo não pode ser vazio");
		
		paciente = pacienteService.save(paciente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(paciente.getId())
				.toUri();

		return ResponseEntity.created(uri).body(paciente);

	}
	
	@PostMapping("/{pacienteId}/endereco")
		public ResponseEntity<Object> insertEndereco(@RequestBody Endereco endereco,@PathVariable Long pacienteId){
			pacienteService.insertEndereco(endereco, pacienteId);
			return ResponseEntity.created(null).body(endereco);
		}
	
	@GetMapping("/{pacienteId}/enderecos")
	public ResponseEntity<List<Endereco>> findAllEnderecos (@PathVariable Long pacienteId){
		return ResponseEntity.ok().body(pacienteService.findAllEnderecosById(pacienteId));
	
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Paciente paciente = pacienteService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não existe com id: " + id));
		
		return ResponseEntity.status(HttpStatus.OK).body(paciente);

	}

	@GetMapping
	public ResponseEntity<Page<Paciente>> findAll(Pageable pageable) {
		return ResponseEntity.ok().body(pacienteService.findsAll(pageable));
	}


	@GetMapping("/pacientes/nome")
	public ResponseEntity<Page<Paciente>> findByNome(@RequestParam String nome, Pageable pageable) {
		Page<Paciente> pacientes = pacienteService.findByNome(nome, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(pacientes);

	}

	@GetMapping("/pacientes/email")
	public ResponseEntity<Page<Paciente>> findByEmail(@RequestParam String email, Pageable pageable) {
		Page<Paciente> pacientes = pacienteService.findByEmail(email, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(pacientes);

	}

	@GetMapping("/pacientes/cpf")
	public ResponseEntity<Page<Paciente>> findByCpf(@RequestParam String cpf, Pageable pageable) {
		Page<Paciente> pacientes = pacienteService.findByCpf(cpf, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(pacientes);

	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		// verifica se paciente existe
		Paciente paciente = pacienteService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não existe com id: " + id));
		
		pacienteService.delete(id);
		return ResponseEntity.ok().body("Paciente excluido com sucesso");
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updates(@PathVariable Long id, @RequestBody Paciente paciente) {
		paciente = pacienteService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não existe com id: " + id));
		
		
		paciente = pacienteService.update(id, paciente);
		return ResponseEntity.ok().body(paciente);
	}
	
	
}

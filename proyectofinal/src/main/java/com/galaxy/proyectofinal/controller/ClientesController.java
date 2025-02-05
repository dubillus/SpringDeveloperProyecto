package com.galaxy.proyectofinal.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.proyectofinal.dtos.ClienteDTO;
import com.galaxy.proyectofinal.entities.Cliente;
import com.galaxy.proyectofinal.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> findAll(){
		return clienteService.findAllActive();
	}
	
	@GetMapping("/{id}")
	public Optional<ClienteDTO> findById(@PathVariable Long id){
		return clienteService.findById(id);
	}
	
	@GetMapping("/findByNombre")
	public List<Cliente> findByNombre(@RequestParam String nombre){
		return clienteService.findAllOrFilterByNombre("%"+nombre+"%");
	}
	
	@PostMapping
	public Cliente save(@RequestBody Cliente clientes) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String fechaHoraCreacion = LocalDateTime.now().format(formatter);
		clientes.setFechaHoraCreacion(fechaHoraCreacion);
		clientes.setFlagEstado(1);
		return clienteService.save(clientes);
	}
	
	@PutMapping("/{id}")
	public Cliente update(@PathVariable Long id,@RequestBody Cliente cliente) {
		return clienteService.udpate(id,cliente);
	}
	
	@PatchMapping("/{id}")
	public Cliente udpateCorreo(@PathVariable Long id, @RequestBody Cliente cliente) {
		return clienteService.udpateCorreo(id, cliente);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}
}

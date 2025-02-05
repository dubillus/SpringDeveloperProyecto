package com.galaxy.proyectofinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.galaxy.proyectofinal.dtos.ClienteDTO;
import com.galaxy.proyectofinal.entities.Cliente;

public interface ClienteService {

	List<Cliente> findAllOrFilterByNombre(String nombre);
	List<Cliente> findAllActive();
	Page<Cliente> findAllPaging(Pageable pageable);
	Optional<ClienteDTO> findById(Long id);
	Cliente save(Cliente cliente);
	Cliente udpate(Long id, Cliente cliente);
	Cliente udpateCorreo(Long id, Cliente cliente);
	void delete(Long id);
}

package com.galaxy.proyectofinal.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.galaxy.proyectofinal.dtos.ClienteDTO;
import com.galaxy.proyectofinal.entities.Cliente;
import com.galaxy.proyectofinal.mappers.ClienteCustomMapper;
import com.galaxy.proyectofinal.repositories.ClientesRepository;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClientesRepository clienteRepository;
	
	@Autowired
	private ClienteCustomMapper clienteCustomMapper;

	@Override
	public List<Cliente> findAllOrFilterByNombre(String nombre) {
		Optional<String> filterNombre = Optional.ofNullable(nombre);
		if(filterNombre.isPresent()) {
			return clienteRepository.findByNombre("%"+nombre+"%");
		}
		return clienteRepository.findAllActive();
	}

	@Override
	public Optional<ClienteDTO> findById(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente no existe"));
		return Optional.ofNullable(clienteCustomMapper.toDTO(cliente));
	}

	@Override
	public Cliente save(Cliente cliente) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String fechaHoraCreacion = LocalDateTime.now().format(formatter);
		cliente.setFechaHoraCreacion(fechaHoraCreacion);
		cliente.setFlagEstado(1);
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente udpate(Long id, Cliente cliente) {
		Optional<Cliente> optCliente = clienteRepository.findById(id);
		if(optCliente.isPresent()) {
			Cliente clienteToUpdate = optCliente.get();
			clienteToUpdate.setNombre(cliente.getNombre());
			clienteToUpdate.setCorreo(cliente.getCorreo());
			clienteToUpdate.setTelefono(cliente.getTelefono());
			clienteToUpdate.setDireccion(cliente.getDireccion());
			return clienteRepository.save(clienteToUpdate);
		}
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente udpateCorreo(Long id, Cliente cliente) {
		Optional<Cliente> optCliente = clienteRepository.findById(id);
		if(!optCliente.isPresent()) {
			throw new RuntimeException("Cliente no existe");
		}
		Cliente clienteToUpdate = optCliente.get();
		clienteToUpdate.setCorreo(cliente.getCorreo());
		return clienteRepository.save(clienteToUpdate);
	}

	@Override
	public void delete(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente no existe"));
		cliente.setFlagEstado(0);
		clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> findAllActive() {
		return clienteRepository.findAllActive();
	}

	@Override
	public Page<Cliente> findAllPaging(Pageable pageable) {
		return clienteRepository.findAllActive(pageable);
	}

}

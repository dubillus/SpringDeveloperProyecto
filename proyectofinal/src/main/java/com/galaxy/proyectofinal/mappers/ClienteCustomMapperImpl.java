package com.galaxy.proyectofinal.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.galaxy.proyectofinal.dtos.ClienteDTO;
import com.galaxy.proyectofinal.entities.Cliente;

@Component
public class ClienteCustomMapperImpl implements ClienteCustomMapper {

	@Override
	public ClienteDTO toDTO(Cliente cliente) {
		return new ClienteDTO(cliente.getClienteNo(), cliente.getNombre(), cliente.getCorreo(), cliente.getTelefono(), cliente.getDireccion(), cliente.getFechaHoraCreacion(), cliente.getFlagEstado());
	}

	@Override
	public List<ClienteDTO> toListDTO(List<Cliente> clientes) {
		return clientes.stream().map(p->toDTO(p)).toList();
	}

	
}

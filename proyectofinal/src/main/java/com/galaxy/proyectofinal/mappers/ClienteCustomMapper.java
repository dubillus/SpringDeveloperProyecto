package com.galaxy.proyectofinal.mappers;

import java.util.List;

import com.galaxy.proyectofinal.dtos.ClienteDTO;
import com.galaxy.proyectofinal.entities.Cliente;

public interface ClienteCustomMapper {
	
	ClienteDTO toDTO(Cliente cliente);
	List<ClienteDTO> toListDTO(List<Cliente> clientes);
}

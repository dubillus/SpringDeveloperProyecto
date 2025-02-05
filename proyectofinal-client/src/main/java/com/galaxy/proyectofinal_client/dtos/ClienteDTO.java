package com.galaxy.proyectofinal_client.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

	private Long clienteNo;
	private String nombre;
	private String correo;
	private String telefono;
	private String direccion;
	private String fechaHoraCreacion;
	private int flagEstado;
}

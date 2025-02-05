package com.galaxy.proyectofinal_client.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

	private Long clienteNo;
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
	@NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ser un correo válido")
	private String correo;
	@NotBlank(message = "El teléfono es obligatorio")
	@Pattern(regexp = "^[0-9]+$", message = "El teléfono solo debe contener números")
	@Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
	
	private String telefono;
	@NotBlank(message = "La dirección es obligatoria")
	private String direccion;
	private String fechaHoraCreacion;
	private int flagEstado;
}

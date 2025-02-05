package com.galaxy.proyectofinal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_no")
	private Long clienteNo;
	private String nombre;
	private String correo;
	private String telefono;
	private String direccion;
	@Column(name = "fecha_hora_creacion")
	private String fechaHoraCreacion;
	@Column(name = "flag_estado")
	private int flagEstado;
}

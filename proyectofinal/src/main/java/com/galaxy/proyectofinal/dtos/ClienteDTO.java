package com.galaxy.proyectofinal.dtos;


public record ClienteDTO (Long clienteNo,
String nombre,
String correo,
String telefono,
String direccion,
String fechaHoraCreacion,
int flagEstado) {}

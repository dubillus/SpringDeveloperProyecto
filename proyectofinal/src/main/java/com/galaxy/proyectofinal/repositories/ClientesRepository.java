package com.galaxy.proyectofinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.galaxy.proyectofinal.entities.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente,Long>{
	
	@Query(value = "SELECT * FROM CLIENTES WHERE flag_estado = 1 and nombre like CONCAT('%', :nombre, '%')",nativeQuery = true)
	List<Cliente> findByNombre(String nombre);
	
	@Query(value = "SELECT * FROM CLIENTES WHERE flag_estado = 1",nativeQuery = true)
	List<Cliente> findAllActive();
}

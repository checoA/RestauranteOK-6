package com.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.entity.Productos;

import java.util.List;

public interface ProductosRepository extends JpaRepository<Productos,Long> {
	List<Productos> findByNombreContaining(String nombre);
}

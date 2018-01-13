package com.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.entity.Productos_Inventario;

public interface ProductosInventarioRepository extends JpaRepository<Productos_Inventario,Long>{

}

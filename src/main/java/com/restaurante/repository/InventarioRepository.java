package com.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.entity.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario,Long>{

}

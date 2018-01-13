package com.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.entity.Gastos;

public interface GastosRepository extends JpaRepository<Gastos,Long>{
	List<Gastos> findByFechaGreaterThanAndFechaLessThan(String fecha_inicio, String fecha_fin);
	List<Gastos> findByFechaGreaterThanAndFechaLessThanAndNombre(String fecha_inicio,String fecha_fin,String nombre);
}

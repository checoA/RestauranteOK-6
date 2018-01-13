package com.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurante.entity.Configuraciones;

public interface ConfiguracionesRepository extends JpaRepository<Configuraciones, Long>{
	Configuraciones findByNombre(String nombre);
}

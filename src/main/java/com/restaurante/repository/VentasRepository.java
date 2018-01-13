package com.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurante.entity.Ventas;

public interface VentasRepository extends JpaRepository<Ventas,Long>{

}

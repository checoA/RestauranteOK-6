package com.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.entity.Detalle_ordenes;
import com.restaurante.entity.Estados;
import com.restaurante.entity.Ordenes;

public interface DetalleOrdenesRepository extends JpaRepository<Detalle_ordenes,Long>{
	List<Detalle_ordenes> findByOrden(Ordenes id);
	List<Detalle_ordenes> findByOrdenAndEstado(Ordenes id,Estados e);
}

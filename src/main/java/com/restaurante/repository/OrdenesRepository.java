package com.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.entity.Estados;
import com.restaurante.entity.Ordenes;
import com.restaurante.entity.Tipos_pago;

public interface OrdenesRepository extends JpaRepository<Ordenes,Long>{
	List<Ordenes> findByEstado(Estados e);
	List<Ordenes> findByEstadoNotAndEstadoNot(Estados e,Estados e1);
	List<Ordenes> findByNumMesa(int num_mesa);
	List<Ordenes> findByEstadoAndFechaGreaterThanAndFechaLessThan(Estados estado,String fecha_inicio,String fecha_fin);
	List<Ordenes> findByEstadoAndFechaGreaterThanAndFechaLessThanAndTipoPago(Estados estado, String fecha_inicio,String fecha_fin,Tipos_pago tipoPAgo);
	Ordenes findByNumMesaAndEstado(int num_mesa,Estados estado);
	Ordenes findByNumParaLlevarAndEstado(int num_mesa,Estados estado);
}

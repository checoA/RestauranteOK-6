package com.restaurante.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.restaurante.entity.Mesas;

public interface MesasRepository extends JpaRepository<Mesas,Long> {
	@Transactional
	void removeByNumMesa(@Param("num_mesa") int num_mesa);
	Mesas findOneByNumMesa(int num_mesa);
	Mesas findFirstByOrderByIdDesc();
	Mesas findByNumMesa(int num_mesa);
}

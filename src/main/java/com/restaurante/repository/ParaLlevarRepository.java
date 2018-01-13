package com.restaurante.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.restaurante.entity.ParaLlevar;

public interface ParaLlevarRepository extends JpaRepository<ParaLlevar, Long>{
	@Transactional
	void removeByNumMesa(@Param("num_mesa") int num_mesa);
	ParaLlevar findOneByNumMesa(int num_mesa);
	ParaLlevar findFirstByOrderByIdDesc();
	ParaLlevar findByNumMesa(int num_mesa);
}

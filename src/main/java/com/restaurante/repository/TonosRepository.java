package com.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.entity.Tonos;

public interface TonosRepository extends JpaRepository<Tonos,Long>{
	Tonos findByEstablecido(boolean e);
}

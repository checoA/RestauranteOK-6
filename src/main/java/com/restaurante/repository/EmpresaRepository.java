package com.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa,Long>{

}

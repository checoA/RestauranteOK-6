package com.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurante.entity.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios,Long>{
	Usuarios findByUserAndPassword(String user,String password);
	Usuarios findByUser(String nombre);
}

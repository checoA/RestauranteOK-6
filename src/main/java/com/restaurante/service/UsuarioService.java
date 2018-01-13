package com.restaurante.service;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;

import com.restaurante.entity.Permisos;
import com.restaurante.entity.Usuarios;
import com.restaurante.repository.UsuarioRepository;
import com.restaurante.commom.Hash;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository ur;
	
	public List<Usuarios> findAll(){
		return ur.findAll();
	}
	
	public Usuarios findById(Long id) {
		return ur.findById(id).get();
	}
	
	public Usuarios findByPassword(String usuario,String password) {
		password = Hash.getHash(password+usuario, "SHA1");
		return ur.findByUserAndPassword(usuario,password);
	}
	
	public Usuarios findByNombre(String nombre) {
		return ur.findByUser(nombre);
	}
	
	public boolean exist(String userName) {
		ExampleMatcher NAME_MATCHER = ExampleMatcher.matching()
	            .withMatcher("user", GenericPropertyMatchers
	            		.ignoreCase());
	Example<Usuarios> example = Example.<Usuarios>of(new Usuarios(userName), NAME_MATCHER);
		return ur.exists(example);
	}
	
	public Usuarios save(Usuarios user) {
		return ur.save(user);
	}
	
	public boolean delete(Usuarios user) {
		try{
			ur.delete(user);
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	
	public Usuarios changuePass(Usuarios user) {
		user.setPassword(Hash.getHash(user.getPassword()+user.getUser(), "SHA1"));
		return ur.save(user);
	}
	
	public Usuarios save(Usuarios user,long rol) {
		Permisos p = new Permisos();
		p.setId(rol);
		user.setPermiso(p);
		user.setPassword(Hash.getHash(user.getPassword()+user.getUser(), "SHA1"));
		return ur.saveAndFlush(user);
	}
	
	public void delete(Long id) {
		ur.deleteById(id);
	}
}

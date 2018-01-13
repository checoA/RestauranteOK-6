package com.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.entity.Categorias;
import com.restaurante.entity.Serializer;
import com.restaurante.service.CategoriasService;

@RestController
public class CategoriasController {
	@Autowired
	private CategoriasService cs;
	
	@RequestMapping(value="/getCategorias",method=RequestMethod.GET)
	@ResponseBody
	public List<Categorias> obtenerCategorias(){
		return cs.findAll();
	}
	
	@RequestMapping(value="/saveCategorias",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer save(@RequestBody Categorias c) {
		Serializer result = new Serializer();
		if(cs.save(c) != null) {
			result.setCreated(true);
		}else {
			result.setCreated(false);
		}
		return result;
	}
	
	@RequestMapping(value ="/deleteCategorias",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer delete(@RequestBody Categorias c) {
		Serializer result = new Serializer();
		result.setDeleted(cs.delete(c));
		return result;
	}
	
}

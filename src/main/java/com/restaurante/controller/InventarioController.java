package com.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.entity.Inventario;
import com.restaurante.entity.Serializer;
import com.restaurante.service.InventarioService;


@RestController
public class InventarioController {
	@Autowired
	private InventarioService is;
	
	@RequestMapping(value="/obtenerInventario",method=RequestMethod.GET)
	@ResponseBody
	public List<Inventario> obtener(){
		return is.findAll();
	}
	
	@RequestMapping(value="/guardarInventario",method = RequestMethod.PUT)
	@ResponseBody
	public Serializer guardar(@RequestBody Inventario i) {
		Serializer result = new Serializer();
		if(is.save(i) != null) {
			result.setCreated(true);
		}
		else {
			result.setCreated(false);
		}
		return result;
	}
	
	@RequestMapping(value="eliminarInventario",method = RequestMethod.PUT)
	@ResponseBody
	public Serializer eliminar(@RequestBody Inventario i) {
		Serializer result = new Serializer();
		result.setDeleted(is.delete(i));
		return result;
	}
}

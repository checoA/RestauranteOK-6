package com.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.entity.Productos;
import com.restaurante.entity.Productos_Inventario;
import com.restaurante.entity.Serializer;
import com.restaurante.service.ProductosService;

@RestController
public class ProductosController {
	@Autowired
	private ProductosService ps;
	
	@RequestMapping(value="/productos",method =RequestMethod.GET)
	@ResponseBody
	public List<Productos> obtenerProductos(){
		return ps.findAll();
	}
	
	@RequestMapping(value="/por_id",method=RequestMethod.PUT)
	@ResponseBody
	public Productos obtenerXID(@RequestBody Long id){
		return ps.findById(id);
	}
	
	@RequestMapping(value="/guardarProducto",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer saveProducto(@RequestBody Productos producto) {
		Serializer result = new Serializer();
		if(ps.save(producto)!= null) {
			result.setCreated(true);
		}
		else {
			result.setCreated(false);
		}
		return result;
	}
	
	@RequestMapping(value="/eliminarPlatillo",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer delete(@RequestBody Productos producto) {
		Serializer result = new Serializer();
		result.setDeleted(ps.delete(producto));
		return result;
	}
	
	@RequestMapping(value="/filtrar",method=RequestMethod.PUT)
	@ResponseBody
	public List<Productos> filtrar(@RequestBody String nombre) {
		return ps.findFilter(nombre);
	}
	
	@RequestMapping(value="/eliminarElemento",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer delete(@RequestBody Productos_Inventario p) {
		Serializer result = new Serializer();
		result.setDeleted(ps.delete(p));
		return result;
	}
}

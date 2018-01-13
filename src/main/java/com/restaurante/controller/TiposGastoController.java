package com.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.entity.Serializer;
import com.restaurante.entity.Tipos_gasto;
import com.restaurante.service.TiposGastoService;

@RestController
public class TiposGastoController {
	@Autowired
	private TiposGastoService tgs;
	
	@RequestMapping(value="/getTG",method=RequestMethod.GET)
	@ResponseBody
	public List<Tipos_gasto> retrieve(){
		return tgs.retrieve();
	}
	
	@RequestMapping(value="/setTG",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer create(@RequestBody Tipos_gasto t) {
		Serializer result = new Serializer();
		if(tgs.create(t)!=null) {
			result.setCreated(true);
		}
		else {
			result.setCreated(false);
		}
		return result;
	}
	
	@RequestMapping(value="/deleteTG",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer delete(@RequestBody Tipos_gasto t) {
		Serializer result = new Serializer();
		result.setDeleted(tgs.delete(t));
		return result;
	}
}

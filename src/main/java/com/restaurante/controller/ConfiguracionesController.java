package com.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.entity.Configuraciones;
import com.restaurante.entity.Serializer;
import com.restaurante.service.ConfiguracionesService;

import java.util.List;

@RestController
public class ConfiguracionesController {
	@Autowired
	private ConfiguracionesService cs;
	
	@RequestMapping(value="/getConfiguraciones",method=RequestMethod.GET)
	@ResponseBody
	public List<Configuraciones> get(){
		return cs.getConfiguraciones();
	}
	
	@RequestMapping(value="/setConfiguracion",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer set(@RequestBody Configuraciones c) {
		Serializer result = new Serializer();
		if(cs.setConfiguracion(c) != null) {
			result.setCreated(true);
		}else {
			result.setCreated(false);
		}
		return result;
	}
	
	@RequestMapping(value="/getConfiguracion",method=RequestMethod.PUT)
	@ResponseBody
	public Configuraciones get(@RequestBody String nombre) {
		return cs.getConfiguraciones(nombre);
	}
	
}

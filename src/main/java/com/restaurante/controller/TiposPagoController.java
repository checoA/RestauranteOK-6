package com.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.service.TiposPagoService;
import com.restaurante.entity.Tipos_pago;

@RestController
public class TiposPagoController {
	@Autowired
	private TiposPagoService tps;
	
	@RequestMapping(value = "/tipos_pago",method = RequestMethod.GET)
	@ResponseBody
	public List<Tipos_pago> obtenerTiposPago(){
		return tps.findAll();
	}
}

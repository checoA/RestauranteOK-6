package com.restaurante.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.entity.Gastos;
import com.restaurante.entity.Serializer;
import com.restaurante.service.GastosService;

@RestController
public class GastosController {
	@Autowired
	private GastosService gs;
	
	@RequestMapping(value="/setG",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer create(@RequestBody Gastos g) {
		try {
			Serializer result = new Serializer();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(g.getFecha());
			Date f = formatter.parse(g.getFecha());
			String fecha = formatter1.format(f);
			g.setFecha(fecha);
			if(gs.create(g) != null) {
				result.setCreated(true);
			}
			else {
				result.setCreated(false);
			}
			return result;
		}catch(ParseException e) {
			return null;
		}
	}
	
	@RequestMapping(value="/reporteGastos/{fecha_fin}",method=RequestMethod.PUT)
	@ResponseBody
	public List<Gastos> obtenerGastosParaReportes(@RequestBody String fecha_inicio,@PathVariable String fecha_fin){
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date fi = formatter.parse(fecha_inicio) ;
			Date ff = formatter1.parse(fecha_fin);
			return gs.get(fecha_inicio, fecha_fin);
		}catch(ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/reporteGastosFiltro/{fecha_fin}/{filtro}",method=RequestMethod.PUT)
	@ResponseBody
	public List<Gastos> obtenerGastosParaReportes(@RequestBody String fecha_inicio,@PathVariable String fecha_fin, @PathVariable String filtro){
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date fi = formatter.parse(fecha_inicio);
			Date ff = formatter1.parse(fecha_fin);
			return gs.get(fecha_inicio, fecha_fin,filtro);
		}catch(ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}

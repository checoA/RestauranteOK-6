package com.restaurante.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.entity.Detalle_ordenes;
import com.restaurante.entity.Estados;
import com.restaurante.entity.Ordenes;
import com.restaurante.entity.Serializer;
import com.restaurante.entity.Tipos_pago;
import com.restaurante.entity.usp_mas_vendidos;
import com.restaurante.service.DetalleOrdenesService;
import com.restaurante.service.MesasService;
import com.restaurante.service.ParaLlevarService;
import com.restaurante.service.OrdenesService;
import com.restaurante.service.usp_mas_vendidos_service;

@RestController
public class OrdenesController {
	@Autowired
	private OrdenesService os;
	@Autowired
	private DetalleOrdenesService dos;
	@Autowired
	private MesasService ms;
	@Autowired
	private ParaLlevarService ps;
	@Autowired
	private MesasController mc;
	@Autowired
	private usp_mas_vendidos_service umvs;
	
	@RequestMapping(value="/ordenById",method=RequestMethod.PUT)
	@ResponseBody
	public Ordenes obtenerOrden(@RequestBody Long id) {
		return os.findById(id);
	}
	
	@RequestMapping(value="/ordenes",method=RequestMethod.GET)
	@ResponseBody
	public List<Ordenes> obtenerOrdenesParaCocina(){
		return os.findByNotEstado();
	}
	
	@RequestMapping(value="/caja",method=RequestMethod.GET)
	@ResponseBody
	public List<Ordenes> obtenerOrdenesParaCaja(){
		return os.findByEstado(5L);
	}
	
	@RequestMapping(value="/reporteVentas/{fecha_fin}",method=RequestMethod.PUT)
	@ResponseBody
	public List<Ordenes> obtenerOrdenesParaReportes(@RequestBody String fecha_inicio,@PathVariable String fecha_fin){
		//return null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date fi = formatter.parse(fecha_inicio) ;
			Date ff = formatter1.parse(fecha_fin);
			return os.findByEstadoAndFecha(formatter.format(fi),formatter1.format(ff));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/reportesVentasFiltro/{fecha_fin}/{filtro}",method=RequestMethod.PUT)
	@ResponseBody
	public List<Ordenes> obtenerOrdenesParaReportes(@RequestBody String fecha_inicio,@PathVariable String fecha_fin, @PathVariable int filtro){
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date fi = formatter.parse(fecha_inicio) ;
			Date ff = formatter1.parse(fecha_fin);
			return os.findByEstadoAndFechaAndTipoPago(formatter.format(fi),formatter1.format(ff), filtro);
		}catch(ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/masVendidos/{fecha_inicio}/{fecha_fin}/{categoria}",method = RequestMethod.PUT)
	@ResponseBody
	public List<usp_mas_vendidos> obtenerProductosMasVendidos(@PathVariable String fecha_inicio,@PathVariable String fecha_fin,@PathVariable String categoria){
		try {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date fi = formatter.parse(fecha_inicio);
		Date ff = formatter1.parse(fecha_fin);
		return umvs.obtenerMasVendidos(formatter.format(fi),formatter1.format(ff),categoria);
		}catch(ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/concretarVenta",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer concretarVenta(@RequestBody Ordenes o) {
		Serializer result = new Serializer();
		//Estados e = new Estados();
		//e.setId(3L);
		//o.setEstado(e);
		//mc.resetearMesa(o.getNumMesa());
		if(os.save(o)!= null) {
			result.setCreated(true);
		}
		else {
			result.setCreated(false);
		}
		return result;
	}
	
	@RequestMapping(value="/concretarVentaParaLlevar",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer concretarVentaParaLlevar(@RequestBody Ordenes o) {
		Serializer result = new Serializer();
		if(os.save(o) != null) {
			result.setCreated(true);
		}
		else {
			result.setCreated(false);
		}
		return result;
	}
	
	@RequestMapping(value="/cancelarVentaParaLlevar/{num_mesa}",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer eliminarOrdenParaLlevar(@RequestBody List<Detalle_ordenes> productos,@PathVariable Long num_mesa) {
		Serializer result = new Serializer();
		dos.save(productos);
		if(os.cancelarOrdenParaLlevar(num_mesa) != null) {
			ps.save(num_mesa);
			result.setCreated(true);
		}
		else {
			result.setCreated(false);
		}
		return result;
	}
	
	@RequestMapping(value="/cancelarVenta/{num_mesa}",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer eliminarOrden(@RequestBody List<Detalle_ordenes> productos,@PathVariable Long num_mesa) {
		Serializer result = new Serializer();
		dos.save(productos);
		if(os.cancelarOrden(num_mesa)!=null) {
			ms.save(num_mesa);
			result.setCreated(true);
		}
		else {
			result.setCreated(false);
		}
		return result;
	}
	
}

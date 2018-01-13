package com.restaurante.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.entity.Estados;
import com.restaurante.entity.Ordenes;
import com.restaurante.entity.Tipos_pago;
import com.restaurante.repository.OrdenesRepository;

@Service
public class OrdenesService {
	@Autowired
	private OrdenesRepository or;
	
	public List<Ordenes> findAll(){
		return or.findAll();
	}
	
	public Ordenes save(Ordenes o) {
		return or.save(o);
	}
	
	public Ordenes findById(Long id) {
		return or.findById(id).get();
	}
	
	public List<Ordenes> findByEstado(Long estado){
		Estados e = new Estados();
		e.setId(estado);
		return or.findByEstado(e);
	}
	
	public List<Ordenes> findByNotEstado(){
		Estados e = new Estados();
		e.setId(3L);
		Estados e1 = new Estados();
		e1.setId(4L);
		return or.findByEstadoNotAndEstadoNot(e,e1);
	}
	
	public Ordenes findByMesa(Long num_mesa){
		Estados e = new Estados();
		e.setId(9L);
		return or.findByNumMesaAndEstado(num_mesa.intValue(), e);
	}
	
	public Ordenes findByMesaParaLlevar(Long num_mesa) {
		Estados e = new Estados();
		e.setId(9L);
		return or.findByNumParaLlevarAndEstado(num_mesa.intValue(),e);
	}
	
	public Ordenes findByMEsa(Long num_mesa,Long estado) {
		Estados e = new Estados();
		e.setId(estado);
		return or.findByNumMesaAndEstado(num_mesa.intValue(), e);
	}
	
	public List<Ordenes> findByEstadoAndFecha(String fecha_inicio,String fecha_fin){
		Estados e = new Estados();
		e.setId(3L);
		Estados e1 = new Estados();
		e1.setId(4L);
		return or.findByEstadoAndFechaGreaterThanAndFechaLessThan(e, fecha_inicio,fecha_fin);
	}
	
	public List<Ordenes> findByEstadoAndFechaAndTipoPago(String fecha_inicio,String fecha_fin,int tp){
		Estados e = new Estados();
		e.setId(3L);
		Estados e1 = new Estados();
		e1.setId(4L);
		Tipos_pago t = new Tipos_pago();
		t.setId((long)tp);
		return or.findByEstadoAndFechaGreaterThanAndFechaLessThanAndTipoPago(e, fecha_inicio, fecha_fin, t);	
	}
	
	
	public Ordenes cancelarOrdenParaLlevar(Long num_mesa) {
		Estados e =new Estados();
		e.setId(9L);
		Ordenes o = or.findByNumParaLlevarAndEstado(num_mesa.intValue(), e);
		if(o == null) {
			e.setId(6L);
			o = or.findByNumParaLlevarAndEstado(num_mesa.intValue(), e);
		}
		e.setId(4L);
		o.setEstado(e);
		o.setTotal(0);
		return or.save(o);
	}
	
	public Ordenes cancelarOrden(Long num_mesa) {
		Estados e =new Estados();
		e.setId(9L);
		Ordenes o = or.findByNumMesaAndEstado(num_mesa.intValue(), e);
		if(o == null) {
			e.setId(6L);
			o = or.findByNumMesaAndEstado(num_mesa.intValue(), e);
		}
		e.setId(4L);
		o.setEstado(e);
		o.setTotal(0);
		return or.save(o);
	}
}

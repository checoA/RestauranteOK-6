package com.restaurante.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.entity.Detalle_ordenes;
import com.restaurante.entity.Estados;
import com.restaurante.entity.Inventario;
import com.restaurante.entity.Ordenes;
import com.restaurante.entity.ParaLlevar;
import com.restaurante.entity.Productos;
import com.restaurante.entity.Productos_Inventario;
import com.restaurante.repository.DetalleOrdenesRepository;
import com.restaurante.repository.InventarioRepository;
import com.restaurante.repository.ParaLlevarRepository;
import com.restaurante.repository.OrdenesRepository;

@Service
public class ParaLlevarService {
	@Autowired 	
	private ParaLlevarRepository	mr;
	@Autowired
	private OrdenesRepository or;
	@Autowired
	private DetalleOrdenesRepository dr;
	@Autowired
	private InventarioRepository ir;
	
	public List<ParaLlevar> findAll(){
		return mr.findAll();
	}
	
	public ParaLlevar create() {		
		ParaLlevar m = new ParaLlevar(mr.count()+1);
		Estados e= new Estados();
		e.setId(7L);
		m.setEstado(e);
		return mr.save(m);
	}
	
	public ParaLlevar findByNumMesa(int num_mesa) {
		return mr.findByNumMesa(num_mesa);
	}
	
	public ParaLlevar save(ParaLlevar m) {
		return mr.save(m);
	}
	
	
	public void delete() {
		ParaLlevar m = mr.findFirstByOrderByIdDesc();
		mr.delete(m);
	}
	
	public void save(Long num_mesa) {
		Estados e = new Estados();
		e.setId(7L);
		ParaLlevar m = mr.findByNumMesa(num_mesa.intValue());
		m.setEstado(e);
		mr.save(m);
	}
	
	public ParaLlevar save(Long id,Detalle_ordenes d,Long estadoOrden) {
		ParaLlevar m = mr.findByNumMesa(id.intValue());
		Estados e = new Estados();
		e.setId(estadoOrden);
		Ordenes o = or.findByNumMesaAndEstado(id.intValue(), e);
		if(o == null) {
			e.setId(5L);
			o = or.findByNumMesaAndEstado(id.intValue(), e);
		}
		if(o == null) {
			e.setId(6L);
			o = or.findByNumMesaAndEstado(id.intValue(), e);
		}
		Estados es = new Estados();
		es.setId(5L);
		d.setEstado(es);
		dr.save(d);
		o.setEstado(es);
		m.setEstado(es);
		or.save(o);
		return mr.save(m);
	}
	
	public ParaLlevar save(Long id, Long estado,Long estadoOrden) {
		ParaLlevar m = mr.findByNumMesa(id.intValue());
		Estados e = new Estados();
		e.setId(estadoOrden);
		Ordenes o = or.findByNumMesaAndEstado(id.intValue(), e);
		if(o == null) {
			e.setId(5L);
			o = or.findByNumMesaAndEstado(id.intValue(), e);
		}
		if(o == null) {
			e.setId(6L);
			o = or.findByNumMesaAndEstado(id.intValue(), e);
		}
		List<Detalle_ordenes> detalles_ordenes;
		if(estado == 6L) {
			Estados es = new Estados();
			es.setId(5L);
			detalles_ordenes = dr.findByOrdenAndEstado(o,es);
		}
		else {
			detalles_ordenes = dr.findByOrden(o);
		}
		for(int i=0;i<detalles_ordenes.size();i++) {
			if(detalles_ordenes.get(i).getEstado().getId() != 4) {
				Productos p = detalles_ordenes.get(i).getProducto();
				List<Productos_Inventario> s = p.getProducto().stream().collect(Collectors.toList());
				for(int j = 0; j < s.size(); j++) {
					Inventario inv = s.get(j).getInventario();
					inv.setExistencia(inv.getExistencia() - s.get(j).getCantidad());
					ir.save(inv);
				}
				e.setId(estado);
				detalles_ordenes.get(i).setEstado(e);
			}
		}
		e.setId(estado);
		o.setEstado(e);
		or.save(o);
		m.setEstado(e);
		return mr.save(m);
	}
}

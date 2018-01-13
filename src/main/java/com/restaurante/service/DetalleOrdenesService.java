package com.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.entity.Detalle_ordenes;
import com.restaurante.entity.Estados;
import com.restaurante.entity.Mesas;
import com.restaurante.entity.ParaLlevar;
import com.restaurante.entity.Ordenes;
import com.restaurante.entity.Productos;
import com.restaurante.entity.Ventas;
import com.restaurante.repository.DetalleOrdenesRepository;
import com.restaurante.repository.MesasRepository;
import com.restaurante.repository.ParaLlevarRepository;
import com.restaurante.repository.OrdenesRepository;
import com.restaurante.repository.VentasRepository;
import com.restaurante.repository.ProductosRepository;

@Service
public class DetalleOrdenesService {
	@Autowired
	private DetalleOrdenesRepository dor;
	@Autowired
	private VentasRepository vr;
	@Autowired
	private OrdenesRepository or;
	@Autowired
	private MesasRepository mr;
	@Autowired
	private ParaLlevarRepository pr;
	@Autowired
	private ProductosRepository pdr;
	
	public List<Detalle_ordenes> saveParaLlevar(List<Detalle_ordenes> d,int num_mesa,boolean b,Long estado,int numClientes,String usuario){
		float total = 0;
		for(int i = 0;i>d.size();i++) {
			total += d.get(i).getPrecio();
		}
		
		Estados e = new Estados();
		e.setId(estado);
		
		Ordenes o = or.findByNumParaLlevarAndEstado(num_mesa, e);
		if(o == null) {
			o =  new Ordenes();
		}
		Estados e1 = new Estados();
		e1.setId(9L);
		o.setTotal(total);
		o.setEstado(e1);
		o.setNumParaLlevar(num_mesa);
		o.setNumMesa(0);
		o.setNumPersonas(numClientes);
		or.save(o);	
		e.setId(1L);
		for(int i = 0;i<d.size();i++) {
/*			Productos p = new Productos();
			p.setId(d.get(i).getProducto().getId());
			d.get(i).setProducto(p);*/
			
			if(d.get(i).getProducto() != null) {
				Productos p = new Productos();
				if(d.get(i).getProducto().getId() != null) {
					p.setId(d.get(i).getProducto().getId());
				}
				else {
					 p = pdr.save(d.get(i).getProducto());
				}
				d.get(i).setProducto(p);
			}
			
			
			if(d.get(i).getEstado() == null || (d.get(i).getEstado().getId() != 4 && d.get(i).getEstado().getId() != 5 && d.get(i).getEstado().getId() != 6)) {
				d.get(i).setEstado(e);
			}
			if(d.get(i).getUsuario() == null || d.get(i).getUsuario() == "") {
				d.get(i).setUsuario(usuario);
			}
			d.get(i).setOrden(o);
		}
		Estados e2 = new Estados();
		e2.setId(8L);
		ParaLlevar m = pr.findByNumMesa(num_mesa);
		m.setEstado(e2);
		pr.save(m);
		
		return dor.saveAll(d);
	}
	
	public List<Detalle_ordenes> save(List<Detalle_ordenes> d,int num_mesa,boolean b,Long estado,int numClientes,String usuario) {		
		float total = 0;
		for(int i = 0;i<d.size();i++) {
			total += d.get(i).getPrecio();
		}
		Estados e = new Estados();
		e.setId(estado);
		Ordenes	o = or.findByNumMesaAndEstado(num_mesa, e);
		if(o == null) {
			o = new Ordenes();
		}
		Estados e1 = new Estados();
		e1.setId(9L);
		o.setTotal(total);
		o.setEstado(e1);
		o.setNumMesa(num_mesa);
		o.setNumParaLlevar(0);
		o.setNumPersonas(numClientes);
		o.setVisto(false);
		or.save(o);
		e.setId(1L);
		for(int i = 0;i<d.size();i++) {
			if(d.get(i).getProducto() != null) {
				Productos p = new Productos();
				if(d.get(i).getProducto().getId() != null) {
					p.setId(d.get(i).getProducto().getId());
				}
				else {
					 p = pdr.save(d.get(i).getProducto());
				}
				d.get(i).setProducto(p);
			}
			
			if(d.get(i).getEstado() == null || (d.get(i).getEstado().getId() != 4 && d.get(i).getEstado().getId() != 5 && d.get(i).getEstado().getId() != 6)) {
				d.get(i).setEstado(e);		
			}
			if(d.get(i).getUsuario() == null || d.get(i).getUsuario() == "") {
				d.get(i).setUsuario(usuario);
			}
			d.get(i).setOrden(o);
		}	
		Estados e2 = new Estados();
		e2.setId(8L);
		Mesas m = mr.findByNumMesa(num_mesa);
		m.setEstado(e2);
		mr.save(m);
		
		return dor.saveAll(d);
	}
	
	public Detalle_ordenes save(Detalle_ordenes producto) {
		Estados e = new Estados();
		e.setId(4L);
		producto.setEstado(e);
		producto.setCantidad(0);
		producto.setPrecio(0);
		return dor.save(producto);
	}
	
	public void setVisto(List<Detalle_ordenes> productos) {
		dor.saveAll(productos);
	}
	
	public void save(List<Detalle_ordenes> productos) {
		Estados e = new Estados();
		e.setId(4L);
		for(int i = 0;i<productos.size();i++) {
			productos.get(i).setEstado(e);
		}
		dor.saveAll(productos);
	}
	
	public List<Detalle_ordenes> findAllOrden(Ordenes orden){
		return dor.findByOrden(orden);
	}
	
	public List<Detalle_ordenes> findAllId(Long id){
		Ordenes o= or.findById(id).get();		
		return dor.findByOrden(o);
	}
}

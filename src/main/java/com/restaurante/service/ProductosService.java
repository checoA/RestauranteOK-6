package com.restaurante.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.entity.Productos;
import com.restaurante.entity.Productos_Inventario;
import com.restaurante.repository.ProductosInventarioRepository;
import com.restaurante.repository.ProductosRepository;

@Service
public class ProductosService {
	@Autowired
	private ProductosRepository pr;
	
	@Autowired
	private ProductosInventarioRepository pir;
	
	public List<Productos> findAll(){
		return pr.findAll();
	}
	
	public List<Productos> findFilter(String filtro){
		return pr.findByNombreContaining(filtro);
	}
	
	public Productos findById(Long id) {
		return pr.findById(id).get();
	}
	
	public Productos save(Productos producto) {
		try {
		Productos p = new Productos();
		p.setId(producto.getId());
		p.setCategoria(producto.getCategoria());
		System.out.println(producto.getNombre());
		p.setNombre(producto.getNombre());
		p.setPrecio(producto.getPrecio());
		
		List<Productos_Inventario> s = producto.getProducto().stream().collect(Collectors.toList());
		
		
		producto.getProducto().clear();
		
		producto = pr.save(p);
		
		for(int i = 0; i< s.size();i++) {
			Productos_Inventario pi = new Productos_Inventario();
			pi.setId(s.get(i).getId());
			pi.setCantidad(s.get(i).getCantidad());
			System.out.println(pi.getCantidad());
			pi.setInventario(s.get(i).getInventario());
			pi.setProductos(p);
			pir.save(pi);
		}
		
		return producto;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public boolean delete(Productos producto) {
		try {
			pir.deleteAll(producto.getProducto());
			pr.deleteById(producto.getId());
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean delete(Productos_Inventario p) {
		try {
			pir.delete(p);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}

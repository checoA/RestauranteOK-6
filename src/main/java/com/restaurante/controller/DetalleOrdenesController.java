package com.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.entity.Detalle_ordenes;
import com.restaurante.entity.Ordenes;
import com.restaurante.entity.Serializer;
import com.restaurante.service.DetalleOrdenesService;
import com.restaurante.service.OrdenesService;

@RestController
public class DetalleOrdenesController {
	@Autowired
	private DetalleOrdenesService dos;
	@Autowired
	private OrdenesService os;
	
	@RequestMapping(value="/getOrden",method=RequestMethod.PUT)
	@ResponseBody
	public List<Detalle_ordenes> obtenerOrdenPorMesa(@RequestBody Long mesa){
		Ordenes o = os.findByMesa(mesa);
		return dos.findAllId(o.getId());
	}
	
	@RequestMapping(value="/getOrdenParaLlevar",method=RequestMethod.PUT)
	@ResponseBody
	public List<Detalle_ordenes> obtenerOrdenPorMesaParaLlevar(@RequestBody Long mesa){
		Ordenes o = os.findByMesaParaLlevar(mesa);
		return dos.findAllId(o.getId());
	}
	
	@RequestMapping(value="/getOrdenEstado/{estado}",method=RequestMethod.PUT)
	@ResponseBody
	public List<Detalle_ordenes> obtenerOrdenesPorMesa(@RequestBody Long mesa,@PathVariable Long estado){
		Ordenes o = os.findByMEsa(mesa, estado);
		if(o == null) {
			o = os.findByMEsa(mesa, 9L);
		}
		return dos.findAllId(o.getId());
	}
	
	@RequestMapping(value="/saveDO/{num_mesa}/{bandera}/{estado}/{numClientes}/{usuario}",method = RequestMethod.PUT)
	@ResponseBody
	public Serializer guardarDO(@RequestBody List<Detalle_ordenes> detalle_ordenes,@PathVariable int num_mesa,@PathVariable boolean bandera,@PathVariable Long estado,@PathVariable int numClientes,@PathVariable String usuario) {
		Serializer result = new Serializer();
		if(estado == 0) {
			estado = 9L;
		}
		if(dos.save(detalle_ordenes,num_mesa,bandera,estado,numClientes,usuario) != null) {
			result.setCreated(true);
		}else {
			result.setCreated(false);
		}
		return result;
	}
	
	@RequestMapping(value="/saveDOParaLlevar/{num_mesa}/{bandera}/{estado}/{numClientes}/{usuario}",method = RequestMethod.PUT)
	@ResponseBody
	public Serializer guardarDOParaLlevar(@RequestBody List<Detalle_ordenes> detalle_ordenes,@PathVariable int num_mesa,@PathVariable boolean bandera,@PathVariable Long estado,@PathVariable int numClientes,@PathVariable String usuario) {
		Serializer result = new Serializer();
		if(estado == 0) {
			estado = 9L;
		}
		if(dos.saveParaLlevar(detalle_ordenes, num_mesa, bandera, estado, numClientes, usuario) != null) {
			result.setCreated(true);
		}else {
			result.setCreated(false);
		}
		return result;
	}
	
	@RequestMapping(value="/ordenDetalles",method=RequestMethod.PUT)
	@ResponseBody
	public List<Detalle_ordenes> obtenerXID(@RequestBody Long id){
		List<Detalle_ordenes> detalle = dos.findAllId(id);
		Ordenes o = os.findById(id);
		o.setVisto(true);
		os.save(o);
		return detalle;
	}
	
	@RequestMapping(value="/setVisto",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer setVisto(@RequestBody Long id) {
		Serializer result = new Serializer();
		List<Detalle_ordenes> detalle = dos.findAllId(id);
		for(int i = 0; i < detalle.size();i++) {
			detalle.get(i).setVisto(true);
		}
		dos.setVisto(detalle);
		result.setCreated(true);
		
		return result;
	}
	
	@RequestMapping(value="/obtenerDO",method=RequestMethod.PUT)
	@ResponseBody
	public List<Detalle_ordenes> obtenerDO(@RequestBody Ordenes orden){
		return dos.findAllOrden(orden);
	}
	
	@RequestMapping(value="/eliminarProducto",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer eliminar(@RequestBody Detalle_ordenes producto) {
		Serializer result = new Serializer();
		if(dos.save(producto) != null) {
			result.setCreated(true);
		}else {
			result.setCreated(false);
		}
		return result;
	}
}

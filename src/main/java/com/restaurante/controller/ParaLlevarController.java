package com.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.entity.Detalle_ordenes;
import com.restaurante.entity.Estados;
import com.restaurante.entity.Ordenes;
import com.restaurante.entity.ParaLlevar;
import com.restaurante.entity.Serializer;
import com.restaurante.service.ParaLlevarService;
import com.restaurante.service.OrdenesService;

@RestController
public class ParaLlevarController {
	@Autowired
	private ParaLlevarService ms;
	@Autowired
	private OrdenesService os;
	
	@RequestMapping(value="/paraLlevar",method = RequestMethod.GET)
	@ResponseBody
	public List<ParaLlevar> obtenerMesas(){
		return ms.findAll();
	}
	
	//@RequestMapping(value="/add")
	//@ResponseBody
	@MessageMapping("/addParaLlevar")
	@SendTo("/topic/obtenerMesasParaLlevar")
	public Serializer agregarMesas() {
		Serializer result = new Serializer();
		try {
			ms.create();
			result.setMesaCreated(true);			
		}catch(Exception e) {
			result.setMesaCreated(false);
			result.setMensaje(e.getMessage());
		}
		return result;
	}
	
	@MessageMapping("/delParaLlevar")
	@SendTo("/topic/obtenerMesasParaLlevar")
	public Serializer eliminarMesas() {
		Serializer result = new Serializer();
		try {
			ms.delete();
			result.setDeleted(true);
		}catch(Exception e) {
			result.setDeleted(false);
			result.setMensaje(e.getMessage());
		}
		return result;
	}
	
	//@MessageMapping("/entregar")
	@RequestMapping(value="/entregarParaLlevar",method=RequestMethod.PUT)
	//@SendTo("/entregas")
	public Serializer entregarV(@RequestBody long id) {
		Serializer result = new Serializer();
		if(ms.save(id,5L,9L) != null) {
			result.setEntregar(true);
		}else {
			result.setEntregar(false);
		}
		return result;
	}
	
	@RequestMapping(value="/entregarProductoParaLlevar/{id}",method=RequestMethod.PUT)
	public Serializer entregarV(@RequestBody Detalle_ordenes detalle, @PathVariable long id) {
		Serializer result = new Serializer();
		if(ms.save(id,detalle,9L) != null) {
			result.setEntregar(true);
		}else {
			result.setEntregar(false);
		}
		return result;
	}
	
	public void resetearMesa(int num_mesa) {
		ParaLlevar m = ms.findByNumMesa(num_mesa);
		Estados e = new Estados();
		e.setId(7L);
		m.setEstado(e);
		ms.save(m);
	}
	
	@RequestMapping(value="/entregadoParaLlevar",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer entregado(@RequestBody Long id) {
		Serializer result = new Serializer();
		if(ms.save(id,6L,5L) != null) {
			result.setEntregado(true);
		}else {
			result.setEntregado(false);
		}
		return result;
	}
	
	@RequestMapping(value="/liberarParaLlevar",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer liberarParaLlevar(@RequestBody Ordenes o) {
		Serializer result = new Serializer();
		ParaLlevar p = ms.findByNumMesa(o.getNumParaLlevar());
		Estados e = new Estados();
		e.setId(3L);
		o.setEstado(e);
		Estados e1= new Estados();
		e1.setId(7L);
		p.setEstado(e1);
		ms.save(p);
		if(os.save(o) != null) {
			result.setCreated(true);
		}
		else {
			result.setCreated(false);
		}
		return result;
	}	
}

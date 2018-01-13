package com.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.service.TonosService;
import com.restaurante.entity.Serializer;
import com.restaurante.entity.Tonos;

import java.util.List;

@RestController
public class TonosController {
	@Autowired
	private TonosService ts;
	
	@RequestMapping(value="/getTonos",method=RequestMethod.GET)
	@ResponseBody
	public List<Tonos> getTonos(){
		return ts.findAll();
	}
	
	@RequestMapping(value="/getTono",method=RequestMethod.GET)
	@ResponseBody
	public Tonos getTono() {
		return ts.getEstablecido();
	}
	
	@RequestMapping(value="/setTono",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer setTono(@RequestBody Tonos t) {
		Serializer result = new Serializer();
		ts.reset();
		t.setEstablecido(true);
		if(ts.save(t) != null) {
			result.setCreated(true);
		}
		else {
			result.setCreated(false);
		}
		return result;
	}
}

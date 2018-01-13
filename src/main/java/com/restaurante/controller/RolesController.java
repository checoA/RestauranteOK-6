package com.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.service.RolesService;
import com.restaurante.entity.Roles;

@RestController
public class RolesController {
	@Autowired
	private RolesService rs;
	
	@RequestMapping(value="/cuentas",method = RequestMethod.GET)
	@ResponseBody
	public List<Roles> obtenerCuentas(){
		return rs.findAll();
	}
}

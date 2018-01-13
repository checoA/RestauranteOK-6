package com.restaurante.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.entity.*;
import com.restaurante.service.PermisosService;
import com.restaurante.service.UsuarioService;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioService us;
	
	@Autowired
	private PermisosService ps;
	
	@RequestMapping(value="/Users",method = RequestMethod.GET)
	@ResponseBody
	public List<Usuarios> index(){
		return us.findAll();
	}
	
	@RequestMapping(value="/exist",method =RequestMethod.PUT)
	@ResponseBody
	public Serializer exist(@RequestBody String user) {
		Serializer result = new Serializer();
		result.setExist(us.exist(user));
		result.setMensaje("El usuario ya existe");
		return result;
	}
	
	@RequestMapping(value="/login",method= RequestMethod.PUT)
    @ResponseBody
	public Serializer login(@RequestBody Usuario user) {
		Serializer result;
		Usuarios u = us.findByPassword(user.getNombre(),user.getPassword());
		if(u!=null) {
			user.setPassword("");
			result = new Serializer(true,user,"");
			result.setPermisos(crearMenu(u.getPermiso()));
		}
		else {
			user.setPassword("");
			result = new Serializer(false,user,"Usuario o contrase�a incorrectos");
		}
		return result;
	}
	
	@RequestMapping(value="/saveROL",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer create(@RequestBody Usuarios user) {
		Serializer result = new Serializer();
		if(us.save(user) != null) {
			ps.save(user.getPermiso());
			result.setCreated(true);
		}
		else {
			result.setCreated(false);
		}
		return result;
	}
	
	@RequestMapping(value="/deleteUser",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer delete(@RequestBody Usuarios user) {
		Serializer result = new Serializer();
		result.setDeleted(us.delete(user));
		return result;
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer update(@RequestBody Usuarios user) {
		Serializer result = new Serializer();
		if(us.changuePass(user) != null) {
			result.setUpdated(true);
		}else {
			result.setUpdated(false);
		}
		return result;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer save(@RequestBody Usuarios user) {
		Serializer result = new Serializer();
		try {
			result.setCreated(true);
			user.getPermiso().setId(null);
			Permisos p = ps.save(user.getPermiso());
			us.save(user,(long)p.getId());
		}
		catch(Exception e) {
			result.setCreated(false);
			result.setMensaje("Error al crear el usuario "+e.toString()+user.getUser());
		}
		return result;
	}
	
	@RequestMapping(value="/comprobar/{nombre}",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer comprobar(@RequestBody String accion,@PathVariable String nombre) {
		Serializer result = new Serializer();
		Usuarios u = us.findByNombre(nombre);
		switch(accion) {
		case "caja":
				result.setCreated(u.getPermiso().isCobrar());
			break;
		case "mesas":
			result.setCreated(u.getPermiso().isVer_mesas());
			break;
		case "paraLlevar":
			result.setCreated(u.getPermiso().isVer_mesas());
			break;
		case "cocina":
			result.setCreated(u.getPermiso().isVer_ordenes());
			break;
		case "platillos":
			result.setCreated(u.getPermiso().isVer_platillos());
			break;
		case "inventario":
			result.setCreated(u.getPermiso().isVer_inventario());
			break;
		case "gastos":
			result.setCreated(u.getPermiso().isVer_gastos());
			break;
		case "reportes":
			result.setCreated(u.getPermiso().isVisualizar_reportes());
			break;
		case "usuarios":
			result.setCreated(u.getPermiso().isVer_usuarios());
			break;
		case "barra":
			result.setCreated(u.getPermiso().isVer_barra());
			break;
/*		case "categorias":
			result.setCreated(u.getPermiso().isVer_paquetes());
			break;*/
		case "empresa":
			result.setCreated(u.getPermiso().isEmpresa());
			break;
		case "otros":
			result.setCreated(u.getPermiso().isAgregar_usuarios());
			break;
		}
		return result;
	}
	
	public List<Menu> crearMenu(Permisos p){
		List<Menu> m = new ArrayList<Menu>();
		Menu temp = new Menu();
		Menu submenu = new Menu();
		
		if(p.isCobrar()) {
			temp = new Menu();
            temp.setAccion("caja");
            temp.setTexto("Caja");
            m.add(temp);
		}
		if(p.isVer_mesas()) {
			temp = new Menu();
			temp.setAccion("mesas");
			temp.setTexto("Mesas");
			m.add(temp);
		}
		if(p.isVer_mesas()) {
			temp = new Menu();
			temp.setAccion("paraLlevar");
			temp.setTexto("Para Llevar");
			m.add(temp);
		}
		if(p.isVer_ordenes()) {
			temp = new Menu();
			temp.setAccion("cocina");
			temp.setTexto("Cocina");
			m.add(temp);
		}
		/*if(p.isVer_barra()) {
			temp = new Menu();
			temp.setAccion("barra");
			temp.setTexto("Barra");
			m.add(temp);
		}*/
		if(p.isVer_platillos() || p.isVer_inventario() || p.isVer_paquetes()) {
			temp =  new Menu();
			temp.setTexto("Productos");
			temp.setSubmenu(new ArrayList<Menu>());
			m.add(temp);
		}
		if(p.isVer_platillos()) {
			submenu = new Menu();
			submenu.setAccion("platillos");
			submenu.setTexto("Platillos");
			temp.getSubmenu().add(submenu);
		}
		if(p.isVer_inventario()) {
			submenu = new Menu();
			submenu.setAccion("inventario");
			submenu.setTexto("Inventario");
			temp.getSubmenu().add(submenu);
		}
		/*if(p.isVer_paquetes()) {
			submenu =  new Menu();
			submenu.setAccion("categorias");
			submenu.setTexto("Categorias");
			temp.getSubmenu().add(submenu);
		}*/
		if(p.isVer_compras() || p.isVer_gastos()) {
			temp = new Menu();
			temp.setTexto("Administracion");
			temp.setSubmenu(new ArrayList<Menu>());
			m.add(temp);
		}
		/*if(p.isVer_compras()) {
			submenu = new Menu();
			submenu.setAccion("compras");
			submenu.setTexto("Compras");
			temp.getSubmenu().add(submenu);
		}*/
		if(p.isVer_gastos()) {
			submenu = new Menu();
			submenu.setAccion("gastos");
			submenu.setTexto("Gastos");
			temp.getSubmenu().add(submenu);
		}
		if(p.isVisualizar_reportes() || p.isVisualizar_estadisticas()) {
			temp = new Menu();
			temp.setTexto("Reportes");
			temp.setSubmenu(new ArrayList<Menu>());
			m.add(temp);
		}
		if(p.isVisualizar_reportes()) {
			submenu = new Menu();
			submenu.setAccion("reportes");
			submenu.setTexto("Reportes");
			temp.getSubmenu().add(submenu);
		}
		/*if(p.isVisualizar_estadisticas()) {
			submenu =  new Menu();
			submenu.setAccion("estadisticas");
			submenu.setTexto("Estadisticas");
			temp.getSubmenu().add(submenu);
		}*/
		if(p.isVer_usuarios() || p.isEmpresa() || p.isAgregar_usuarios()) {
			temp = new Menu();
			temp.setTexto("Configuracion");
			temp.setSubmenu(new ArrayList<Menu>());
			m.add(temp);
		}
		if(p.isVer_usuarios()) {
			submenu = new Menu();
			submenu.setAccion("usuarios");
			submenu.setTexto("Usuarios");
			temp.getSubmenu().add(submenu);
		}
		if(p.isEmpresa()) {
			submenu = new Menu();
			submenu.setTexto("Empresa");
			submenu.setAccion("empresa");
			temp.getSubmenu().add(submenu);
		}
		if(p.isAgregar_usuarios()) {
			submenu = new Menu();
			submenu.setTexto("Otros");
			submenu.setAccion("otros");
			temp.getSubmenu().add(submenu);
		}
		temp = new Menu();
		temp.setTexto("Salir");
		temp.setAccion("logout");
		m.add(temp);
		
		return m;
	}
	
}

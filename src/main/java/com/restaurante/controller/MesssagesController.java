package com.restaurante.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.entity.Message;
import com.restaurante.entity.Serializer;

@Controller
public class MesssagesController {
	@MessageMapping("/sendMessage")
	@SendTo("/topic/messages")
	public Message receive(Message message) throws Exception {
		Thread.sleep(3000); // simulated delay
		System.out.println("entrando");
		return message;
	}
	
	@MessageMapping("/setEntregado")
	@SendTo("/topic/obtenerMesasAgregadas")
	public Serializer b(Serializer p)throws Exception{
		return p;
	}
	
	@MessageMapping("/setEntregar")
	@SendTo("/topic/obtenerMesasAgregadas")
	public Serializer a(Serializer p)throws Exception{
		return p;
	}
	
	@MessageMapping("/setCocina")
	@SendTo("/topic/obtenerMesasAgregadas")
	public Serializer s(Serializer p) throws Exception{
		return p;
	}
	
	@MessageMapping("/unsetEntregarAlert")
	@SendTo("/topic/unsetEntregar")
	public Serializer c()throws Exception{
		Serializer result = new Serializer();
		return result;
	}
	
	@MessageMapping("/unsetCocinaAlert")
	@SendTo("/topic/unsetCocina")
	public Serializer d()throws Exception{
		Serializer result = new Serializer();
		return result;
	}
	
}

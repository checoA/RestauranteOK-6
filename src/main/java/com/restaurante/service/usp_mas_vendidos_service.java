package com.restaurante.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.restaurante.entity.usp_mas_vendidos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class usp_mas_vendidos_service {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<usp_mas_vendidos> obtenerMasVendidos(String fecha_inicio,String fecha_fin,String categoria) {
		
		
		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("usp_mas_vendidos");
		storedProcedure.registerStoredProcedureParameter("fechaInicio", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("fechaFin", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("categoria",String.class,ParameterMode.IN);
		storedProcedure.setParameter("fechaInicio", fecha_inicio);
		storedProcedure.setParameter("fechaFin", fecha_fin);
		storedProcedure.setParameter("categoria",""+categoria);
		System.out.println(fecha_inicio);
		System.out.println(fecha_fin);
		List<Object[]> storedProcedureResults = storedProcedure.getResultList();
		
		return storedProcedureResults.stream().map(result -> new usp_mas_vendidos(
		         (String) result[0],
		         (BigDecimal) result[1],
		         (BigInteger) result[2]
		   )).collect(Collectors.toList());
		
	}
}

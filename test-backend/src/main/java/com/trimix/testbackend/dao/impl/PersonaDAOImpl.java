package com.trimix.testbackend.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.trimix.testbackend.dao.PersonaDAO;
import com.trimix.testbackend.model.Persona;

@Repository
public class PersonaDAOImpl extends BaseDAOImpl<Persona, Long> implements PersonaDAO{
	
	private static final Logger LOG = LogManager.getLogger(PersonaDAOImpl.class);
	
	@Override
	public List<Persona> findByNombreTipoDocumento(String nombre, String tipoDoc){
		LOG.info("Begin findByNombreTipoDocumento");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" 1=1");
		
		if(nombre != null){
			sb.append(" AND upper(perNombre) LIKE '%").append(nombre.toUpperCase()).append("%'");
			
		}
		
		if(tipoDoc != null){
			sb.append(" AND perTipoDocumento = '").append(tipoDoc).append("'");
		}
		
		return super.getListHQL(sb.toString());
	}
	
}

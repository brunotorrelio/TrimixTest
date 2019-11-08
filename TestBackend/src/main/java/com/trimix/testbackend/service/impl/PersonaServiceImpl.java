package com.trimix.testbackend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trimix.testbackend.dao.PersonaDAO;
import com.trimix.testbackend.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService{
	
	@Autowired
	private PersonaDAO personaDAO;
	
	@Override
	public PersonaDAO getPersonaDAO(){
		return personaDAO;
	}

	public void setPersonaDAO(PersonaDAO personaDAO) {
		this.personaDAO = personaDAO;
	}
	
	
	
}

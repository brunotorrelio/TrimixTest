package com.trimix.testbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.trimix.testbackend.service.PersonaService;

public class BaseController {
	
	@Autowired
	private PersonaService personaService;

	public PersonaService getPersonaService() {
		return personaService;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}
	
	
}	

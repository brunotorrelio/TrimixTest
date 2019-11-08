package com.trimix.testbackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trimix.testbackend.dto.PersonaDTO;
import com.trimix.testbackend.model.Persona;
import com.trimix.testbackend.model.Persona.TipoDocumento;
import com.trimix.testbackend.service.PersonaService;
import com.trimix.testbackend.util.Utils;

@RestController
@RequestMapping("/persona")
public class PersonaController extends BaseController{
	
	private static final Logger LOG = LogManager.getLogger(PersonaController.class);
	
	
	@GetMapping("/list")
	public Map<String, Object> listPersonas(@RequestParam String nombre, @RequestParam String tipoDoc,
			HttpServletRequest request){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			if(nombre.trim().isEmpty()){
				nombre = null;
			}
			
			if(tipoDoc.trim().isEmpty()){
				tipoDoc = null;
			}

			List<Persona> listPersonas = getPersonaService().getPersonaDAO().findByNombreTipoDocumento(nombre, tipoDoc);
			
			List<PersonaDTO> listPersonasDTO = this.listPersonaToPersonaDTO(listPersonas);
			
			response.put("status", "success");
			response.put("list", listPersonasDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "error");
			response.put("message", "se ha producido un error al intentar listar a las personas");
		}
		
		
		return response;
		
	}
	
	private List<PersonaDTO> listPersonaToPersonaDTO(List<Persona> listPersonas){
		
		List<PersonaDTO> listaDTO = new ArrayList<PersonaDTO>();
		
		for(Persona persona : listPersonas){
			
			listaDTO.add(this.personaToPersonaDTO(persona));
		}
		
		return listaDTO;
	}
	
	private PersonaDTO personaToPersonaDTO(Persona persona){
		PersonaDTO itemDTO = new PersonaDTO();
		
		itemDTO.setPerId(persona.getPerId());
		itemDTO.setPerApellido(persona.getPerApellido());
		itemDTO.setPerNombre(persona.getPerNombre());
		itemDTO.setPerTipoDocumento(persona.getPerTipoDocumento());
		itemDTO.setPerNumeroDocumento(persona.getPerNumeroDocumento());
		itemDTO.setPerFechaNacimientoString(Utils.getLocalDateString(persona.getPerFechaNacimiento(), "dd/MM/yyyy"));
		
		return itemDTO;
	}
	
	@GetMapping("/tiposDocumento")
	public Map<String, Object> listTiposDocumento(HttpServletRequest request){
		LOG.info("Begin listTiposDocumento");
		
		Map<String, Object> response = new HashMap<String, Object>();
		List<String> listTiposDocumentos = new ArrayList<String>();
		
		try {
			
			for(TipoDocumento tipoDocumento : Persona.TipoDocumento.values()){
				listTiposDocumentos.add(tipoDocumento.name());
			}
			
			response.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "error");
		}
		response.put("list", listTiposDocumentos);
		return response;
	}
	
	@GetMapping("/{id}")
	public Map<String, Object> getPersona(@PathVariable Long id){
		LOG.info("Begin getPersona");
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			Persona persona = getPersonaService().getPersonaDAO().getById(id);
			
			response.put("status", "success");
			response.put("persona", this.personaToPersonaDTO(persona));
			
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "error");
			response.put("message", "Se produjo un error al intentar acceder a esta persona");
		}
		
		return response;
	}
	
	@PostMapping("/save")
	public Map<String, Object> saveOrEdit(@RequestBody PersonaDTO personaDTO){
		LOG.info("Begin saveOrEdit");
		
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			
			LOG.info(personaDTO.getPerApellido());
			
			if(personaDTO.getPerId() == null){
				LOG.info("save");
				
				Persona persona = new Persona();
				
				persona.setPerApellido(personaDTO.getPerApellido());
				persona.setPerNombre(personaDTO.getPerNombre());
				persona.setPerTipoDocumento(personaDTO.getPerTipoDocumento());
				persona.setPerNumeroDocumento(personaDTO.getPerNumeroDocumento());
				persona.setPerFechaNacimiento(Utils.getLocalDate(personaDTO.getPerFechaNacimientoString(), "dd/MM/yyyy"));
				
				getPersonaService().getPersonaDAO().save(persona);
				
			}
			else {
				LOG.info("update");
				
				Persona persona = getPersonaService().getPersonaDAO().getById(personaDTO.getPerId());
				persona.setPerApellido(personaDTO.getPerApellido());
				persona.setPerNombre(personaDTO.getPerNombre());
				persona.setPerTipoDocumento(personaDTO.getPerTipoDocumento());
				persona.setPerNumeroDocumento(personaDTO.getPerNumeroDocumento());
				persona.setPerFechaNacimiento(Utils.getLocalDate(personaDTO.getPerFechaNacimientoString(), "dd/MM/yyyy"));
				
				getPersonaService().getPersonaDAO().edit(persona);
			}
			response.put("status", "success");
			
		} catch (Exception e) {
			
			String message = "Se ha producido un error al intentar ";
			
			if(personaDTO.getPerId() == null){
				message = message.concat("dar de alta a una persona");
			}
			else {
				message = message.concat("modificar a esta persona");
			}
			
			e.printStackTrace();
			response.put("status", "error");
			response.put("message", message);
		}
		
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String, Object> deletePersona(@PathVariable Long id){
		LOG.info("Begin deletePersona");
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			getPersonaService().getPersonaDAO().deleteById(id);
			response.put("status", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "error");
			response.put("message", "Se ha producido un error al intentar eliminar a esta persona");
		}
		
		return response;
	}
}

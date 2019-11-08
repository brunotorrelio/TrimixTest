package com.trimix.testbackend.dao;

import java.util.List;

import com.trimix.testbackend.model.Persona;

public interface PersonaDAO extends BaseDAO<Persona, Long>{

	List<Persona> findByNombreTipoDocumento(String nombre, String tipoDoc);

}

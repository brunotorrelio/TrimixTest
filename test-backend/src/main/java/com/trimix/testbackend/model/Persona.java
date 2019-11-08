package com.trimix.testbackend.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "personas", schema = "public")
public class Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8887548241579620136L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_generator")
	@SequenceGenerator(name = "persona_generator", sequenceName = "personas_seq", schema = "public", allocationSize = 1)
	@Column(name = "per_id")
	private Long perId;
	
	@Column(name = "per_apellido")
	private String perApellido;
	
	@Column(name = "per_nombre")
	private String perNombre;
	
	@Column(name = "per_numero_documento")
	private Long perNumeroDocumento;
	
	@Column(name = "per_tipo_documento")
	private String perTipoDocumento;
	
	@Column(name = "per_fecha_nacimiento")
	private LocalDate perFechaNacimiento;
	
	public static enum TipoDocumento {
		DNI, PASAPORTE, CEDULA
	}
	

	public Long getPerId() {
		return perId;
	}

	public void setPerId(Long perId) {
		this.perId = perId;
	}

	public String getPerApellido() {
		return perApellido;
	}

	public void setPerApellido(String perApellido) {
		this.perApellido = perApellido;
	}

	public String getPerNombre() {
		return perNombre;
	}

	public void setPerNombre(String perNombre) {
		this.perNombre = perNombre;
	}

	public Long getPerNumeroDocumento() {
		return perNumeroDocumento;
	}

	public void setPerNumeroDocumento(Long perNumeroDocumento) {
		this.perNumeroDocumento = perNumeroDocumento;
	}

	public String getPerTipoDocumento() {
		return perTipoDocumento;
	}

	public void setPerTipoDocumento(String perTipoDocumento) {
		this.perTipoDocumento = perTipoDocumento;
	}

	public LocalDate getPerFechaNacimiento() {
		return perFechaNacimiento;
	}

	public void setPerFechaNacimiento(LocalDate perFechaNacimiento) {
		this.perFechaNacimiento = perFechaNacimiento;
	}
	
}

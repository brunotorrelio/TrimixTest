package com.trimix.testbackend.dto;

public class PersonaDTO {
	
	private Long perId;
	
	private String perApellido;
	
	private String perNombre;
	
	private Long perNumeroDocumento;
	
	private String perTipoDocumento;
	
	private String perFechaNacimientoString;

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

	public String getPerFechaNacimientoString() {
		return perFechaNacimientoString;
	}

	public void setPerFechaNacimientoString(String perFechaNacimientoString) {
		this.perFechaNacimientoString = perFechaNacimientoString;
	}
	
	
}

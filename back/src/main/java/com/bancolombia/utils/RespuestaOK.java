package com.bancolombia.utils;

public enum RespuestaOK {
	OK("OK"), NOK("NoOK");
	
	private String respuesta;
	
	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	private RespuestaOK(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
}

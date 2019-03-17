package com.bancolombia.utils;


/**
 * Esta clase realiza la conversion para los servicios REST
 * de la aplicacion
 * responseCode: Codigo del response
 * message: se envia el mensaje general OK, NoOK
 * objeto: referencia ya sean objetos devueltos o mensajes de error
 * @author User
 *
 */
public class RestResponse {
	private Integer responseCode;
	private String message;
	private Object objeto;

	public RestResponse(Integer code) {
		super();
		this.responseCode = code;
	}

	public RestResponse(Integer code, String message, Object objeto) {
		super();
		this.responseCode = code;
		this.message = message;
		this.objeto = objeto;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getObjeto() {
		return objeto;
	}
	
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	

}

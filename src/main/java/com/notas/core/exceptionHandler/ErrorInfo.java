package com.notas.core.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorInfo {
	
	@JsonProperty("mensaje")
	private String mensaje;
	@JsonProperty("status_code")
	private String statusCode;
	@JsonProperty("uri")
	private String uriRequested;
	
	public ErrorInfo(String statusCode, String mensaje, String uriRequested) {
		this.mensaje = mensaje;
		this.statusCode = statusCode;
		this.uriRequested = uriRequested;
	}
	
	public ErrorInfo(int statusCode, String mensaje, String uriRequested) {
		this(Integer.valueOf(statusCode).toString(), mensaje, uriRequested);
	}
	
	public String getMensaje() {
		return mensaje;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public String getUriRequested() {
		return uriRequested;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public void setUriRequested(String uriRequested) {
		this.uriRequested = uriRequested;
	}
}

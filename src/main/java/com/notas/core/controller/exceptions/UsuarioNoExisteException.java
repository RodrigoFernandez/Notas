package com.notas.core.controller.exceptions;

public class UsuarioNoExisteException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public UsuarioNoExisteException(String mensaje) {
		super(mensaje);
	}
}

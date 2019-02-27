package com.notas.core.controller.exceptions;

public class NotaNoExisteException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NotaNoExisteException(String mensaje) {
		super(mensaje);
	}
}

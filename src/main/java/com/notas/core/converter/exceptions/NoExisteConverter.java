package com.notas.core.converter.exceptions;

public class NoExisteConverter extends Exception {
	private static final long serialVersionUID = -2769922765791649532L;
	
	/**
	 * mirar:
	 * 
	 * http://www.springboottutorial.com/spring-boot-exception-handling-for-rest-services
	 * https://github.com/in28minutes/spring-boot-examples/tree/master/spring-boot-2-rest-service-exception-handling
	 **/
	public NoExisteConverter(String nombreConverter) {
		super("No existe converter: " + nombreConverter);
	}
}

package br.com.nekicard.neki.exception;

public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 3454640414540638688L;

	public NotFoundException(String message) {
		super(message);
	}
}

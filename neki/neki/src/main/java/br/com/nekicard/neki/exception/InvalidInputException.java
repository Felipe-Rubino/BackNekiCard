package br.com.nekicard.neki.exception;

public class InvalidInputException extends RuntimeException{
	
	private static final long serialVersionUID = 2170178871795742264L;

	public InvalidInputException(String message) {
		super(message);
	}
}

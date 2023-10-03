package br.com.nekicard.neki.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class MessageExceptionHandler {
	
	
	private Date date;

	private HttpStatus status;

	private String message;

	public MessageExceptionHandler(Date date, HttpStatus notFound, String message) {
		this.date = date;
		this.status = notFound;
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setTimestamp(Date date) {
		this.date = date;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

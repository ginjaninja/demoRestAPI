package com.ginjaninja.demoRestAPI.message;

import java.io.Serializable;

public class Message {
	public static enum Type {
        ERROR, WARNING, INFO, SUCCESS;
	}
	
	private String message;
	private final Type status;
	private final Serializable result;
	
	public Message(Type status, String message){
		this.status = status;
		this.message = message;
		this.result = null;
	}
	
	public Message(Type status, String message, Serializable result){
		this.status = status;
		this.message = message;
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Type getStatus() {
		return status;
	}

	public Object getResult() {
		return result;
	}
	
}

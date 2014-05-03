package com.ginjaninja.restAPI.message;

import java.io.Serializable;

public class Message {
	public static enum Type {
        ERROR, WARNING, INFO, SUCCESS;
	}
	
	private String message;
	private final Type type;
	private final Serializable result;
	
	public Message(Type type, String message){
		this.type = type;
		this.message = message;
		this.result = null;
	}
	
	public Message(Type type, String message, Serializable result){
		this.type = type;
		this.message = message;
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Type getType() {
		return type;
	}

	public Object getResult() {
		return result;
	}
	
}

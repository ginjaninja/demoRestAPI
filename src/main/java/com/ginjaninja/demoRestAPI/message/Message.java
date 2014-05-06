package com.ginjaninja.demoRestAPI.message;

import java.io.Serializable;

/**
 * Message builder for ResponseEntity<Message>. Returns formatted message converted to json via @ResponseBody.
 *
 */
public class Message {
	public static enum Type {
        ERROR, WARNING, INFO, SUCCESS;
	}
	
	private String text;
	private final Type type;
	private final Serializable result;
	
	/**
	 * Construct message without result object;
	 * @param type Type
	 * @param text String
	 * @return Message 
	 */
	public Message(Type type, String text){
		this.type = type;
		this.text = text;
		this.result = null;
	}
	
	/**
     * Construct message without result object;
     * @param type Type
     * @param text String
     * @param result Serializable
     * @return Message 
     */
	public Message(Type type, String text, Serializable result){
		this.type = type;
		this.text = text;
		this.result = result;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Type getType() {
		return type;
	}

	public Object getResult() {
		return result;
	}
	
}

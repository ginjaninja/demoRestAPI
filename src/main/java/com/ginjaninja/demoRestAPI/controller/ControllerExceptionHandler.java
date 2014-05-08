package com.ginjaninja.demoRestAPI.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ginjaninja.demoRestAPI.message.Message;

@Controller
public abstract class ControllerExceptionHandler {
	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<Message> handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex){
		Message message = new Message(Message.Type.ERROR, "Type mismatch. Please check your request.");
		return new ResponseEntity<Message>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<Message> handleIllegalArgumentException(HttpServletRequest req, IllegalArgumentException ex){
		Message message = new Message(Message.Type.ERROR, "Cannot update or delete entity not in database.");
		return new ResponseEntity<Message>(message, HttpStatus.NOT_FOUND);
	}

}

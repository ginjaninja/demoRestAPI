package com.ginjaninja.demoRestAPI.home;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for requests to /
 *
 */
@Controller
@RequestMapping(value={"/"})
public class HomeController {
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String index() {
	    Message message = new Message(Message.Type.SUCCESS, "OK");
        return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Message> teapotIndex() {
	    Message message = new Message(Message.Type.WARNING, "Be careful, teapots can be dangerous.");
		return new ResponseEntity<Message>(message, HttpStatus.I_AM_A_TEAPOT);
	}
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.DELETE})
	@ResponseBody
    public ResponseEntity<Message> unimplementedIndex() {
	    Message message = new Message(Message.Type.ERROR, "Reqest methods PUT and DELETE are not implemented for this address.");
        return new ResponseEntity<Message>(message, HttpStatus.NOT_IMPLEMENTED);
    }
}

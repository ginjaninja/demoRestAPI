package com.ginjaninja.demoRestAPI.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.core.subst.Token.Type;

import com.ginjaninja.restAPI.dao.GenericDAO;
import com.ginjaninja.restAPI.message.Message;

@Controller
@RequestMapping(value={"person"})
public class PersonController {
	@Autowired
	PersonDAOImpl personDAO;
	//GenericDAO<Person, Long> personDAO;
	
	/*
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Map>
	*/
	
	@RequestMapping(method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Message> save(@RequestBody final Person person, BindingResult bindingResult){
		Message message;
		if(bindingResult.hasErrors()){
			message = new Message(Message.Type.ERROR, "Poorly formed JSON object");
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		}
		personDAO.save(person);
		
		if(person.getId() != null){
			message = new Message(Message.Type.SUCCESS, "OK", person);
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}else{
			message = new Message(Message.Type.ERROR, "Could not save person");
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> update(@RequestBody final Person person, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			//return error code for this and other calls if person is not properly formed json
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		personDAO.update(person);
		if(person.getId() != null){
			return new ResponseEntity<String>(person.getId().toString(), HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("ERROR", HttpStatus.OK);
		}
	}
}

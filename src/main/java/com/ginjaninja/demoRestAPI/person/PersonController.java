package com.ginjaninja.demoRestAPI.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ginjaninja.restAPI.message.Message;

@Controller
@RequestMapping(value={"person"})
public class PersonController {
	@Autowired
	PersonDAOImpl personDAO;
	//GenericDAO<Person, Long> personDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Message> getAll(){
		Message message;
		People people = new People(personDAO.getAll());
		message = new Message(Message.Type.SUCCESS, "OK", people);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Message> get(@PathVariable Long id){
		Message message;
		Person person = personDAO.get(id);
		if(person == null){
			message = new Message(Message.Type.ERROR, "No results found");
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}else{
			message = new Message(Message.Type.SUCCESS, "OK", person);
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
	}
	
	
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
		if(person.getId() == null){
			message = new Message(Message.Type.ERROR, "Could not save person");
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}else{
			message = new Message(Message.Type.SUCCESS, "OK", person);
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Message> update(@RequestBody final Person person, BindingResult bindingResult){
		Message message;
		if(bindingResult.hasErrors()){
			message = new Message(Message.Type.ERROR, "Poorly formed JSON object");
			return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
		}
		personDAO.update(person);
		if(person.getId() == null){
			message = new Message(Message.Type.ERROR, "Could not update person");
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}else{
			message = new Message(Message.Type.SUCCESS, "OK", person);
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Message> delete(@RequestBody final Person person, BindingResult bindingResult){
		Message message;
		if(bindingResult.hasErrors()){
			message = new Message(Message.Type.ERROR, "Poorly formed JSON object");
			return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
		}
		personDAO.delete(person);
		message = new Message(Message.Type.SUCCESS, "OK");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Message> delete(@PathVariable Long id){
		Person person = personDAO.get(id);
		personDAO.delete(person);
		Message message = new Message(Message.Type.SUCCESS, "OK");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
}

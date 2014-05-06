package com.ginjaninja.demoRestAPI.person;

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

import com.ginjaninja.demoRestAPI.message.Message;

@Controller
@RequestMapping(value={"person"})
public class PersonController {
	@Autowired
	PersonService personService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Message> getAll(){
		Message message;
		People people = new People(personService.getAll());
		message = new Message(Message.Type.SUCCESS, "OK", people);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Message> get(@PathVariable Integer id){
		Message message;
		Person person = personService.get(id);
		if(person == null){
			message = new Message(Message.Type.ERROR, "Person not found");
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
		personService.save(person);
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
		personService.update(person);
		if(person.getId() == null){
			message = new Message(Message.Type.ERROR, "Could not update person");
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}else{
			message = new Message(Message.Type.SUCCESS, "OK", person);
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Message> delete(@PathVariable Integer id){
		personService.delete(id);
		Message message = new Message(Message.Type.SUCCESS, "OK");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
}

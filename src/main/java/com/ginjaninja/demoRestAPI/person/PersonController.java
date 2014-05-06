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

import com.ginjaninja.demoRestAPI.controller.ControllerInterface;
import com.ginjaninja.demoRestAPI.message.Message;

/**
 * Controller for requests to /person
 *
 */
@Controller
@RequestMapping(value={"person"})
public class PersonController implements ControllerInterface<Person>{
	@Autowired
	PersonService personService;
	
	/**
	 * Get all people
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Message> getAll(){
		People people = new People(personService.getAll());
		Message message = new Message(Message.Type.SUCCESS, "OK", people);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
		
	}
	
	/**
	 * Get person by id
	 * @param id Integer
	 * @return ResponseEntity<Message>(message, status)
	 */
	@Override
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Message> get(@PathVariable Integer id){
		Message message;
		HttpStatus status;
		Person person = personService.get(id);
		if(person == null){
			message = new Message(Message.Type.ERROR, "Person not found");
			status = HttpStatus.UNPROCESSABLE_ENTITY;
		}else{
			message = new Message(Message.Type.SUCCESS, "OK", person);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<Message>(message, status);
	}
	
	/**
	 * Save person from json object
	 * @param person Person
	 * @return ResponseEntity<Message>(message, status)
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Message> save(@RequestBody final Person person, BindingResult bindingResult){
		Message message;
		HttpStatus status;
		if(bindingResult.hasErrors()){
			message = new Message(Message.Type.ERROR, "Poorly formed JSON object");
			status = HttpStatus.BAD_REQUEST;
		}else{
		    personService.save(person);
	        if(person.getId() == null){
	            message = new Message(Message.Type.ERROR, "Could not save person");
	            status = HttpStatus.UNPROCESSABLE_ENTITY;
	        }else{
	            message = new Message(Message.Type.SUCCESS, "OK", person);
	            status = HttpStatus.OK;
	        }
		}
		return new ResponseEntity<Message>(message, status);
	}
	
	/**
	 * Update person
	 * @param person Person
	 * @return ResponseEntity<Message>(message, status)
	 */
	@Override
	@RequestMapping(method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Message> update(@RequestBody final Person person, BindingResult bindingResult){
		Message message;
		HttpStatus status;
		if(bindingResult.hasErrors()){
			message = new Message(Message.Type.ERROR, "Poorly formed JSON object");
			status = HttpStatus.BAD_REQUEST;
		}else{
		    personService.update(person);
	        if(person.getId() == null){
	            message = new Message(Message.Type.ERROR, "Could not update person");
	            status = HttpStatus.UNPROCESSABLE_ENTITY;
	        }else{
	            message = new Message(Message.Type.SUCCESS, "OK", person);
	            status = HttpStatus.OK;
	        }
		}
		return new ResponseEntity<Message>(message, status);
	}
	
	/**
	 * Delete person by id
	 * @param id Integer
	 * @return ResponseEntity<Message>(message, status)
	 */
	@Override
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Message> delete(@PathVariable Integer id){
		personService.delete(id);
		Message message = new Message(Message.Type.SUCCESS, "OK");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
}

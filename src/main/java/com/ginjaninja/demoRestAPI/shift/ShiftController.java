package com.ginjaninja.demoRestAPI.shift;

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

import com.ginjaninja.demoRestAPI.controller.ControllerExceptionHandler;
import com.ginjaninja.demoRestAPI.controller.ControllerInterface;
import com.ginjaninja.demoRestAPI.message.Message;

/**
 * Controller for requests to /shift
 *
 */
@Controller
@RequestMapping(value={"shift"})
public class ShiftController extends ControllerExceptionHandler implements ControllerInterface<Shift> {
	@Autowired
	ShiftService shiftService;
	
	/**
	 * Get all shifts
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Message> getAll(){
		ShiftList shifts = new ShiftList(shiftService.getAll());
		Message message = new Message(Message.Type.SUCCESS, "OK", shifts);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
		
	}
	
	/**
	 * Get shift by id
	 * @param id Integer
	 * @return ResponseEntity<Message>(message, status)
	 */
	@Override
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Message> get(@PathVariable Integer id) {
		Message message;
		HttpStatus status;
		Shift shift = shiftService.get(id);
		if(shift == null){
			message = new Message(Message.Type.ERROR, "Shift not found.");
			status = HttpStatus.UNPROCESSABLE_ENTITY;
		}else{
			message = new Message(Message.Type.SUCCESS, "OK", shift);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<Message>(message, status);
	}

	/**
	 * Save shift from json object
	 * @param shift Shift
	 * @return ResponseEntity<Message>(message, status)
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Message> save(@RequestBody final Shift shift, BindingResult bindingResult){
		Message message;
		HttpStatus status;
		if(bindingResult.hasErrors()){
			message = new Message(Message.Type.ERROR, "Poorly formed JSON object");
			status = HttpStatus.BAD_REQUEST;
		}else{
		    shiftService.save(shift);
	        if(shift.getId() == null){
	            message = new Message(Message.Type.ERROR, "Could not save shift");
	            status = HttpStatus.UNPROCESSABLE_ENTITY;
	        }else{
	            message = new Message(Message.Type.SUCCESS, "OK", shift);
	            status = HttpStatus.OK;
	        }
		}
		return new ResponseEntity<Message>(message, status);
	}

	/**
	 * Update shift
	 * @param shift Shift
	 * @return ResponseEntity<Message>(message, status)
	 */
	@Override
	@RequestMapping(method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Message> update(@RequestBody final Shift shift, BindingResult bindingResult){
		Message message;
		HttpStatus status;
		if(bindingResult.hasErrors()){
			message = new Message(Message.Type.ERROR, "Poorly formed JSON object");
			status = HttpStatus.BAD_REQUEST;
		}else{
		    Shift rShift = shiftService.update(shift);
	        if(rShift == null){
	            message = new Message(Message.Type.ERROR, "Could not update shift. Check that values are valid.");
	            status = HttpStatus.UNPROCESSABLE_ENTITY;
	        }else{
	            message = new Message(Message.Type.SUCCESS, "OK", shift);
	            status = HttpStatus.OK;
	        }
		}
		return new ResponseEntity<Message>(message, status);
	}

	/**
	 * Delete shift by id
	 * @param id Integer
	 * @return ResponseEntity<Message>(message, status)
	 */
	@Override
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Message> delete(@PathVariable Integer id){
		Message message;
		HttpStatus status;
		Boolean bool = shiftService.delete(id);
		if(bool){
			message = new Message(Message.Type.SUCCESS, "OK");
			status = HttpStatus.OK;
		}else{
			message = new Message(Message.Type.ERROR, "Shift not found. Could not delete shift.");
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Message>(message, status);
	}
	
	/**
	 * Unimplemented delete
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Message> delete(){
		Message message = new Message(Message.Type.ERROR, "Reqest method DELETE is not allowed at this address.");
		return new ResponseEntity<Message>(message, HttpStatus.METHOD_NOT_ALLOWED);
	}

}

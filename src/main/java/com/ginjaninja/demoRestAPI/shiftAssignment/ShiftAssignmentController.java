package com.ginjaninja.demoRestAPI.shiftAssignment;

import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ginjaninja.demoRestAPI.controller.ControllerExceptionHandler;
import com.ginjaninja.demoRestAPI.message.Message;

/**
 * Controller for requests to /assign
 *
 */
@Controller
@RequestMapping(value={"assign"})
public class ShiftAssignmentController extends ControllerExceptionHandler {
	@Autowired
	ShiftAssignmentService shiftAssignmentService;
	
	/**
	 * Get assignment by id
	 * @param id Integer
	 * @return ResponseEntity<Message>(message, status)
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Message> get(@PathVariable Integer id) {
		Message message;
		HttpStatus status;
		ShiftAssignment assignment = shiftAssignmentService.get(id);
		if(assignment == null){
			message = new Message(Message.Type.ERROR, "Shift assignment not found.");
			status = HttpStatus.UNPROCESSABLE_ENTITY;
		}else{
			message = new Message(Message.Type.SUCCESS, "OK", assignment);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<Message>(message, status);
	}
	
	
	/**
	 * Fetch all Shift Assignments for person for date range
	 * @param startDtTm	Date
	 * @param endDtTm	Date
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, params={"startDtTm", "endDtTm"})
	@ResponseBody
	public ResponseEntity<Message> get(@RequestParam(value="startDtTm",required=true) Date startDtTm, 
			@RequestParam(value="endDtTm",required=true) Date endDtTm) {
		ShiftAssignmentList assignments = new ShiftAssignmentList(shiftAssignmentService.findActiveForDateRange(startDtTm, endDtTm));
		Message message = new Message(Message.Type.SUCCESS, "OK", assignments);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	

	/**
	 * Fetch all Shift Assignments for or shift person for date range
	 * @param startDtTm	Date
	 * @param endDtTm	Date
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, params={"startDtTm", "endDtTm", "personId", "shiftId"})
	@ResponseBody
	public ResponseEntity<Message> get(@RequestParam(value="startDtTm",required=true) Date startDtTm, 
			@RequestParam(value="endDtTm",required=true) Date endDtTm,
			@RequestParam(value="personId") Integer personId,
			@RequestParam(value="shiftId") Integer shiftId) {
		ShiftAssignmentList assignments = 
				new ShiftAssignmentList(shiftAssignmentService.getShiftsForDateRange(startDtTm, endDtTm, personId, shiftId));
		Message message = new Message(Message.Type.SUCCESS, "OK", assignments);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	/**
	 * Save assignment from json object
	 * @param 	ShiftAssignmentDTO shiftAssignmentDTO
	 * @return 	ResponseEntity<Message>(message, status)
	 */
	@RequestMapping(method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Message> save(@RequestBody final ShiftAssignmentDTO shiftAssignmentDTO, BindingResult bindingResult) {
		Message message;
		HttpStatus status;
		if(bindingResult.hasErrors()){
			message = new Message(Message.Type.ERROR, "Poorly formed request.");
			status = HttpStatus.BAD_REQUEST;
		}else{
		    ShiftAssignment assignment = new ShiftAssignment();
		    assignment.convertFromDTO(shiftAssignmentDTO);
		    shiftAssignmentService.save(assignment, shiftAssignmentDTO.getCheckConflict());
		    
		    if(assignment.getId() == null){
		    	message = new Message(Message.Type.ERROR, "Could not save shift assignment.");
	            status = HttpStatus.UNPROCESSABLE_ENTITY;
		    }else{
	            message = new Message(Message.Type.SUCCESS, "OK", assignment);
	            status = HttpStatus.OK;
	        }
		}
		return new ResponseEntity<Message>(message, status);
	}

	
	/**
	 * Update assignment
	 * @param assignment {@link ShiftAssignment}
	 * @return ResponseEntity<Message>(message, status)
	 */
	@RequestMapping(method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Message> update(@RequestBody final ShiftAssignmentDTO shiftAssignmentDTO, BindingResult bindingResult){
		Message message;
		HttpStatus status;
		if(bindingResult.hasErrors()){
			message = new Message(Message.Type.ERROR, "Poorly formed JSON object");
			status = HttpStatus.BAD_REQUEST;
		}else{
			ShiftAssignment assignment = new ShiftAssignment();
		    assignment.convertFromDTO(shiftAssignmentDTO);
		    ShiftAssignment resAssignment = shiftAssignmentService.update(assignment);
		    if(resAssignment == null){
		    	message = new Message(Message.Type.ERROR, "Could not update shift assignment.");
	            status = HttpStatus.UNPROCESSABLE_ENTITY;
		    }else{
		    	message = new Message(Message.Type.SUCCESS, "OK", assignment);
	            status = HttpStatus.OK;
		    }
		}
		return new ResponseEntity<Message>(message, status);
	}

	/**
	 * Delete {@link ShiftAssignment} by id
	 * @param id Integer
	 * @return ResponseEntity<Message>(message, status)
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Message> delete(@PathVariable Integer id){
		Message message;
		HttpStatus status;
		Boolean bool = shiftAssignmentService.delete(id);
		if(bool){
			message = new Message(Message.Type.SUCCESS, "OK");
			status = HttpStatus.OK;
		}else{
			message = new Message(Message.Type.ERROR, "Shift assignment not found. Could not delete assignment.");
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

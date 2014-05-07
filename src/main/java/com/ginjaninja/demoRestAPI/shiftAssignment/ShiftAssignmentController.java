package com.ginjaninja.demoRestAPI.shiftAssignment;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ginjaninja.demoRestAPI.controller.ControllerExceptionHandler;
import com.ginjaninja.demoRestAPI.controller.ControllerInterface;
import com.ginjaninja.demoRestAPI.message.Message;

/**
 * Controller for requests to /assigment
 *
 */
@Controller
@RequestMapping(value={"assigment"})
public class ShiftAssignmentController extends ControllerExceptionHandler implements ControllerInterface<ShiftAssignment> {

	@Override
	public ResponseEntity<Message> get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Message> save(ShiftAssignment t,
			BindingResult bindingResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Message> update(ShiftAssignment t,
			BindingResult bindingResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Message> delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

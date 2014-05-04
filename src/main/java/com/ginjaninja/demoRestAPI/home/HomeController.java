package com.ginjaninja.demoRestAPI.home;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value={"/"})
public class HomeController {
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String index() {
		return "OK";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> badIndex() {
		return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
	}
}

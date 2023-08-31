package com.javateam.springParamProc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest action
 */
@RestController // since Spring 4.0
// @Controller
// @ResponseBody
public class RestDemoController {
	
	private static final Logger log = LoggerFactory.getLogger(RestDemoController.class);
	
	@RequestMapping(value="restAction", 
					method= {RequestMethod.GET, RequestMethod.POST})
	public String restAction(@RequestParam("name") String name,
						     @RequestParam("grade") int grade) {
		
		String msg = "";
		
		log.info("RestController action");
		log.info("name : " + name);
		log.info("grade : " + grade);
		
		msg = "name : " + name + "," + "grade : "+ grade;
		
		return msg; 
	} //
	
	// @GetMapping("restAction2")  // since Spring 4.3
	@PostMapping(value="restAction2", produces = "text/plain; charset=UTF-8")
	public String restAction2(@RequestParam("name") String name,
		     				  @RequestParam("grade") int grade)  {
	
		String msg = "";
		
		log.info("RestController action2");
		log.info("name : " + name);
		log.info("grade : " + grade);
		
		msg = "name : " + name + "," + "grade : "+ grade;
		
		return msg; 
	} //
	
}
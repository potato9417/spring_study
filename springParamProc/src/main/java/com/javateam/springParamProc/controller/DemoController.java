package com.javateam.springParamProc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

@Controller // UML : stereotype
@Slf4j
public class DemoController {
	
	
	@GetMapping("demo.do/{name}")
	public String demo(@PathVariable("name") String name, 
				       @MatrixVariable("grade") String grade, Model model) {
		
		log.info("name : " + name);
		log.info("grade : " + grade);
		
		model.addAttribute("grade", grade);
		
		return "demo"; // demo.jsp (forward)
	}
}
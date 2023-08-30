package com.javateam.demo;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	
	private static final Logger log 
		= LoggerFactory.getLogger(DemoController.class);  
	
	@GetMapping("/demo.do")
	public String demo(HttpServletRequest request, Model model) {
		
		log.info("---- demo.do ------");
		
		// 인자 => JSP (forward)
		request.setAttribute("arg1", 11);
		model.addAttribute("arg2", 17);
		
		return "demo"; // demo.jsp
	}

}

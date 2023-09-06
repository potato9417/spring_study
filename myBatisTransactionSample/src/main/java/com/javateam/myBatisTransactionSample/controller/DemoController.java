package com.javateam.myBatisTransactionSample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DemoController {
	
	@GetMapping("/form")
	public String form() {
		
		log.info("form");
		
		return "form";
	}
	
	@PostMapping("/action")
	public String action(@RequestParam("arg") String arg) {
		
		log.info("한글 처리 여부 : " + arg);
		
		return "form";
	}

}

package com.javateam.springTransaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javateam.springTransaction.service.TransactionServiceMyBatis;

/**
 * home
 */
@Controller
public class HomeController {
	
	@Autowired
	private TransactionServiceMyBatis txService;
	
	@RequestMapping("/")
	public String home(Model model) {
		
		model.addAttribute("members", txService.getAllMembers());
		return "list";
	}
	
}
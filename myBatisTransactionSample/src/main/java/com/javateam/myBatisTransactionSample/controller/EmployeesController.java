package com.javateam.myBatisTransactionSample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javateam.myBatisTransactionSample.domain.Employees;
import com.javateam.myBatisTransactionSample.service.EmployeesService;

@Controller
public class EmployeesController {

	 @Autowired
	 private EmployeesService employeesService;
	 
	 @RequestMapping("/")
	 public String home() {
		
		return "redirect:/employeesList";
	 }

	 @RequestMapping("/employeesList")
	 public ModelAndView getEmployeesList(){

		 ModelAndView result = new ModelAndView();
		 List<Employees> employeesList = employeesService.getEmployeesList();

		 result.addObject("employeesList", employeesList);
		 result.setViewName("/employeesList");

		 return result; 

	 }

	 /*
	 
	 @RequestMapping("/employeesList") 
	 public String getEmployeesList(HttpServletRequest request) {

		 List<Employees> employeesList = employeesService.getEmployeesList();
		 request.setAttribute("employeesList",  employeesList);
		 
		 return "/employeesList";
	 }
*/
	 @RequestMapping("/member")
	 public String getMember(@RequestParam("id") int id,
			 				Model model) {
		 
		 model.addAttribute("member", 
				 			employeesService.getMember(id));
		 
		 return "/member";
	 }
}
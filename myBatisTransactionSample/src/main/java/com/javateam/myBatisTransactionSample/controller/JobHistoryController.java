package com.javateam.myBatisTransactionSample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.myBatisTransactionSample.domain.JobHistory;
import com.javateam.myBatisTransactionSample.service.JobHistoryService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class JobHistoryController {
	
	@Autowired
	private JobHistoryService jobHistoryService;

	@GetMapping("/jobHistory")
	public String jobHistory(@RequestParam("id") int id, Model model) {
		
		log.info("jobHistory Controller : {}", id);
		
		JobHistory jobHistoryVO = jobHistoryService.getJobHistory(id);
		
		log.info("jobHistoryVO : {}", jobHistoryVO);
		
		model.addAttribute("jobHistory", jobHistoryVO);
		
		return "jobHistory"; // jobHistory.jsp 
	}
	
}

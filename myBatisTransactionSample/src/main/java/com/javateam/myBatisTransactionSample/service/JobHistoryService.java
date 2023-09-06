package com.javateam.myBatisTransactionSample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.myBatisTransactionSample.dao.JobHistoryDAO;
import com.javateam.myBatisTransactionSample.domain.JobHistory;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobHistoryService {
	
	@Autowired
	private JobHistoryDAO jobHistoryDAO;
	
	@Transactional(readOnly = true)
	public JobHistory getJobHistory(int employeeId) {
		
		log.info("service getJobHistory : {}", employeeId);		
		return jobHistoryDAO.getJobHistory(employeeId);
	}

}
package com.javateam.myBatisTransactionSample.service;

import java.util.List;

import com.javateam.myBatisTransactionSample.domain.Employees;

public interface EmployeesService {

	List<Employees> getEmployeesList();
	Employees getMember(int employeeId);
	
	// 추가
	void insertMember(Employees employees);

}
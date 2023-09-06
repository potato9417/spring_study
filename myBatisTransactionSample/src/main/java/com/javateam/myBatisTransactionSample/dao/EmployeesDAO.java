package com.javateam.myBatisTransactionSample.dao;

import java.util.List;

import com.javateam.myBatisTransactionSample.domain.Employees;

public interface EmployeesDAO {
	
	List<Employees> getEmployeesList();
	Employees getMember(int employeeId);
	
	// 추가
	void insertMember(Employees employees);

}
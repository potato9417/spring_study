package com.javateam.myBatisTransactionSample.domain;

import java.util.List;

public interface EmployeesMapper {

	 List<Employees> getEmployeesList();
	 Employees getMember(int employeeId);

	 // 추가
	 void insertMember(Employees employees);
}
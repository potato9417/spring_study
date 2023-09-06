package com.javateam.myBatisTransactionSample.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.myBatisTransactionSample.dao.EmployeesDAO;
import com.javateam.myBatisTransactionSample.domain.Employees;

@Service("employeesService")
public class EmployeesServiceImpl implements EmployeesService {

	@Autowired
	private EmployeesDAO employeesDAO;

	@Transactional(readOnly=true, rollbackFor = Exception.class)
	public List<Employees> getEmployeesList() {

		return employeesDAO.getEmployeesList();
	}

	@Transactional(readOnly=true, rollbackFor = Exception.class)
	@Override
	public Employees getMember(int employeeId) {
		
		return employeesDAO.getMember(employeeId);
	} //

	// 추가
	@Transactional(propagation = Propagation.REQUIRED, 
	   	   	   	   rollbackFor = Exception.class)
	@Override
	public void insertMember(Employees employees) {
		
		employeesDAO.insertMember(employees);
	}

}
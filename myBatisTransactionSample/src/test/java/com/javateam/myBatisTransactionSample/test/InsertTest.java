/**
 * 
 */
package com.javateam.myBatisTransactionSample.test;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.myBatisTransactionSample.config.DbConfig;
import com.javateam.myBatisTransactionSample.config.WebConfig;
import com.javateam.myBatisTransactionSample.dao.EmployeesDAO;
import com.javateam.myBatisTransactionSample.domain.Employees;

import lombok.extern.slf4j.Slf4j;

/**
 * @author javateam
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
//      "classpath:/spring/db-context.xml"})
@ContextConfiguration(classes= {DbConfig.class, WebConfig.class})
@WebAppConfiguration 
@Slf4j
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class InsertTest {
	
	@Autowired
	@Qualifier("employeesDAO")
	private EmployeesDAO employeesDAO;
	
	@Test
    @Transactional(propagation=Propagation.REQUIRES_NEW)    
	@Rollback(true) // DB에 반영하지 않고 rollback !	
	// @Rollback(false) // DB에 반영 !
	public void testTransaction() {
		
		log.info("######### 트랜잭션 insert 테스트 ##############");
				
		Employees employees 
			= Employees.builder()
		               .employeeId(300)
		               .firstName("James")
		               .lastName("Kim")
		               .email("JKIM")
		               .phoneNumber("511.111.2323")
		               .hireDate(Date.valueOf("2023-09-04"))
		               .jobId("SH_CLERK")
		               .salary(90000)
		               .managerId(100)
		               .departmentId(50)
			           .build();
		
		log.info("employees : {}", employees);
		
		employeesDAO.insertMember(employees);
	} //

}

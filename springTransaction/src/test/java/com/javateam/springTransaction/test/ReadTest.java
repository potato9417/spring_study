/**
 * 
 */
package com.javateam.springTransaction.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.springTransaction.dao.TransactionDAOMyBatis;
import com.javateam.springTransaction.domain.MemberVO;

/**
 * @author javateam
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/resources/spring/root-context.xml"})
@WebAppConfiguration 
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class ReadTest {
	
	private static final Logger log 
		= LoggerFactory.getLogger(ReadTest.class);
	
	/*
	 * ERROR 프로그램이 중지될 정도는 아닌 에러 이벤트 
	 * WARN  잠재적인 위험 
	 * INFO  대략적으로 프로그램의 진행 상황을 강조 
	 * DEBUG 응용 프로그램을 디버깅하는 데 가장 유용한 세밀한 정보 
	 * TRACE DEBUG보다 세분화된 정보 이벤트
	 */
	
	@Autowired
	private TransactionDAOMyBatis dao;
	
	@Test
	public void testGetAllMembers() {
		
		log.info("######### 트랜잭션 read 테스트 ##############");
		
		assertNotEquals(0, dao.getAllMembers().size()); // 레코드 1개이상 들어가야 true
	} //
	
	@Test
	public void testGetMember() {
		
		log.info("######### 트랜잭션 read 테스트-2 ##############");
		
		assertEquals("스프링맨", dao.getMember("spring1234").getName());
	} //

}
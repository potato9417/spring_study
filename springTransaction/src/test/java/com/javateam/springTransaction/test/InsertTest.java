/**
 * 
 */
package com.javateam.springTransaction.test;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.springTransaction.dao.TransactionDAOMyBatis;
import com.javateam.springTransaction.domain.MemberVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author javateam
 * JUnit테스트로 run 해야함
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/resources/spring/root-context.xml"})
@WebAppConfiguration 
@Slf4j
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class InsertTest {
	
	@Autowired
	private TransactionDAOMyBatis dao;
	
	@Test
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    // @Rollback(true) // DB에 반영하지 않고 rollback ! => 값만 확인하는 테스트용
	@Rollback(false) // DB에 반영 !
	public void testTransaction() {
		
		log.info("######### 트랜잭션 insert 테스트 ##############");		
		
		MemberVO member = new MemberVO(); 
		member.setId("spring1234");
		member.setPw("11111111");
		member.setName("스프링맨");
		member.setAddress("구로디지털단지");
		member.setJoindate(new Date(System.currentTimeMillis()));
		
		dao.insertMember(member);
	} //

}

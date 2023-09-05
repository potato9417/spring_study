package com.javateam.springTransaction.service.test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.springTransaction.domain.MemberVO;
import com.javateam.springTransaction.service.TransactionServiceMyBatis;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring/root-context.xml"})
@Slf4j
public class InsertServiceTest {
	
	// @Slf4j 를 사용하면 아래 코드를 사용하지 않아도 log 자동생성
	// private static final Logger log = LoggerFactory.getLogger(InsertServiceTest.class);
	
	// 테스트 대상 객체 주입(injection)
	@Autowired
	TransactionServiceMyBatis service;
	
	// 테스트 대상 메서드의 인자(객체) 선언
	MemberVO memberVO;

	// 선처리 : 인자값 준비
	// @BeforeClass
	@Before
	public void setUp() throws Exception {
		
		// 기본 방식
		// memberVO = new MemberVO();
		// memberVO.setId("spring111");
		// memberVO.setPw("123456789");
		// memberVO.setName("자바맨");
		// memberVO.setAddress("신도림");
		// memberVO.setJoindate(Date.valueOf("2023-09-05"));
		
		// 빌더(Builder) 방식 => MemberVO에 @Builder 함
		memberVO = MemberVO.builder()
						   .id("spring111")
						   .pw("123456789")
		   				   .name("자바맨")
						   .address("신도림")
						   .joindate(Date.valueOf("2023-09-05"))
						   .build();
				
		
	}

	@Transactional(propagation = Propagation.REQUIRED, 
			       rollbackFor = Exception.class)
	@Rollback(true) // DB에 레코드 삽입하지 않고 로그만 확인 =>테스트용
	// @Rollback(false) // DB에 레코드 삽입 => 실제
	@Test
	public void testInsertMember2() {
		
		log.info("service insertMember2 test");
		
		assertTrue(service.insertMember2(memberVO));
		
	}

}

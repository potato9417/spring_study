package com.javateam.springTransaction.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

// hamcrest 정적 단정 메서드
// import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
// import static org.hamcrest.CoreMatchers.is; // 1) 비추천
// import static org.hamcrest.core.Is.is; // 1) 비추천
import static org.hamcrest.CoreMatchers.isA;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.springTransaction.domain.MemberVO;
import com.javateam.springTransaction.service.TransactionServiceMyBatis;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"file:src/main/resources/spring/root-context.xml", 
//		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@ContextConfiguration("file:src/main/resources/spring/root-context.xml")
@Slf4j
public class InsertServiceHamcrestTest {

//	private static final Logger log 
//		= LoggerFactory.getLogger(InsertServiceTest.class);
	
	// 테스트 대상 객체 주입(injection)
	@Autowired
	TransactionServiceMyBatis service;
	
	// 테스트 대상 메서드의 인자(객체)
	MemberVO memberVO;
	
	// 전처리(선처리) : 인자값 준비
	@Before
	public void setUp() throws Exception {
		
//		memberVO = new MemberVO();
//		memberVO.setId("spring1111");
//		memberVO.setPw("123456789");
//		memberVO.setName("자바맨");
//		memberVO.setAddress("신도림");
//		memberVO.setJoindate(Date.valueOf("2023-09-05"));
		memberVO = MemberVO.builder()
						   .id("spring1111")
						   .pw("123456789")
						   .name("자바맨")
						   .address("신도림")
						   .joindate(Date.valueOf("2023-09-05"))
						   .build();
	}

	@Transactional(propagation = Propagation.REQUIRED, 
				   rollbackFor = Exception.class)
	// @Rollback(true) // DB에 레코드 삽입(X) => 테스트만 수행 !
	@Rollback(false) // DB에 레코드 삽입(O)
	@Test
	public void testInsertMember2() {
		
		log.info("service insertMember2 test");
		
		// ID 중복 : bad case test
		// memberVO.setId("spring1234");
		
		// 기대값 : true
		// assertTrue(service.insertMember2(memberVO));
		// assertFalse(service.insertMember2(memberVO));
		
		// hamcrest 방식 : 동의 표현
		// assertThat(true, is(service.insertMember2(memberVO))); // 비추천 1)
		assertThat(service.insertMember2(memberVO), equalTo(true)); // 추천 2)
		// assertThat(service.insertMember2(memberVO), is(equalTo(true))); // 비추천 2)
		// assertThat(service.insertMember2(memberVO), isA(Boolean.class)); // 추천(대체) 3)
		
	} //
		
	@Test
	public void testGetMember() {
		
		MemberVO memberVO2 = service.getMember("spring1111");
		log.info("memberVO : {}", memberVO);
		log.info("memberVO2 : {}", memberVO2);
		
		// assertTrue(memberVO.equals(memberVO2));
		// assertEquals(memberVO, memberVO2);
		
		// hamcrest 방식 : 동의 표현
		// assertThat(memberVO2, equalTo(memberVO));		
		// assertThat(memberVO2, is(equalTo(memberVO))); // (O)
		assertThat(memberVO2, isA(MemberVO.class));
	}
}

package com.javateam.springTransaction.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Aspect
@Order(1)
@Slf4j
public class MyBatisAdvice2 {
	
	// @Pointcut("execution(* com.javateam.springTransaction.service.TransactionServiceMyBatis.*(..))")
	@Pointcut("execution(* *..TransactionServiceMyBatis.*(..))")
	public void pointcutMyBatis() {
	}
	
	@Before("pointcutMyBatis()")
	public void adviceBefore() {
		log.info("AOP : 회원 모듈 실행 시작 !");
	}
	
	@After("pointcutMyBatis()")
	public void adviceAfter() {
		log.info("AOP : 회원 모듈 실행 끝 !");
	}
	
	@Around("pointcutMyBatis()")
	public Object logAround(ProceedingJoinPoint pjp) {
		
		Object obj = null;
		
		try {
				log.info("AOP Around begin : "
						+ pjp.getSignature().getName()
					    + " 메소드를 시작합니다-2.");
				
				 obj = pjp.proceed();
				 // pjp.proceed(); // 타겟 객체의 원본 객체 실행 
				 // 주의사항) Object 리턴을 하지 않고 void 리턴일 경우는 
				 // 실행이 중단됨.
				 
				log.info("AOP Around end : "
						+ pjp.getSignature().getName()
						+ " 메소드를 끝냈습니다-2.");
				
		} catch (Throwable e) {
			log.info("logAround Exception : " 
							  + e.getMessage());
		}		
		
		return obj;
	} 

}
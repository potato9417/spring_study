package com.javateam.myBatisTransactionSample.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.myBatisTransactionSample.domain.JobHistory;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JobHistoryDAO {
	
	// MyBatis session 객체 주입/생성(와이어링)
	// @Inject // JEE
	@Autowired // Spring => 인스턴스 생성
	private SqlSession sqlSession;
	
	public JobHistory getJobHistory(int employeeId) {
		
		log.info("getJobHistory : {}", employeeId);
		return sqlSession.selectOne("mapper.JobHistoryMapper.getJobHistory", employeeId);
	}

}

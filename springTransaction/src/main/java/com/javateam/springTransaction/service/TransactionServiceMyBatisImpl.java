package com.javateam.springTransaction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.javateam.springTransaction.dao.TransactionDAOMyBatis;
import com.javateam.springTransaction.domain.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceMyBatisImpl implements TransactionServiceMyBatis {
	
	@Autowired
	private TransactionDAOMyBatis dao;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Autowired
	private TransactionTemplate transactionTemplate;

	@Override
	public void insertMember(MemberVO member) {
		
		log.info("tx service insertMember");

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				
				try {	
						dao.insertMember(member);						
			
				} catch(Exception e) {
					status.setRollbackOnly(); // method call -> transaction rollback 
					log.debug("insertMember Exception : " +e.getMessage());
				}

			}

		});		
		
		/*
		 *  MyBatis guide : http://www.mybatis.org/spring/ko/transactions.html
		 *  
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transactionManager.getTransaction(def);
		try {
				dao.insertMember(member);
				transactionManager.commit(status);
		}
		catch (Exception e) {
			transactionManager.rollback(status);
		  // throw ex;
			e.printStackTrace();
		}
		
		 */
	}

	/*
	@Override
	public MemberVO getMember(String id) {

		log.info("tx service getMember");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		MemberVO member = new MemberVO();
		
		try {
				member = dao.getMember(id);
				transactionManager.commit(status);
			
		} catch(Exception e) {
			transactionManager.rollback(status);
			log.debug("getMember Exception : " +e.getMessage());
		}
		
		return member;	
	} */
	
	@Transactional(propagation=Propagation.REQUIRED, 
				   isolation=Isolation.DEFAULT, 
				   rollbackFor=Exception.class)
	@Override
	public MemberVO getMember(String id) {

		log.info("tx service getMember");
		
		return dao.getMember(id);	
	}


	@Override
	public void updateMember(MemberVO member) {

		log.info("tx service updateMember");
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
	
				try {	
						dao.updateMember(member);
						log.info("update success !");
	
				} catch(Exception e) {
					status.setRollbackOnly(); // method call -> transaction rollback 
					log.debug("updateMember Exception : " + e.getMessage());
				} //
			}

		});
		
	} //

	@Override
	public void deleteMember(String id) {

		log.info("tx service deleteMember");
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				
				try {
						dao.deleteMember(id);
						log.info("status flag : " +status.isCompleted());
					
				} catch(Exception e) {
					status.setRollbackOnly(); 
					log.debug("deleteMember Exception : "+e.getMessage());
				} //
			}
	
		});
		
	}

	@Override
	public List<MemberVO> getAllMembers() {

		log.info("tx service getAllMembers");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
				list = dao.getAllMembers();
				transactionManager.commit(status);
				log.info("status flag : " + status.isCompleted());
			
		} catch(Exception e) {
			transactionManager.rollback(status);
			log.debug("getAllMembers Exception : " + e.getMessage());
		} //
		
		return list;
	} //

	@Override
	public boolean insertMember2(MemberVO member) {

		boolean result = false;
		
		result = transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				
				boolean result = false;
				
				try {
					
					dao.insertMember(member);
					result = true;
					log.info("service insertMember2 성공");
				}
				catch(Exception e) {
					
					status.setRollbackOnly();
					result = false;
					log.error("service insertMember2 : {}",e);
					e.printStackTrace();
				}
				
				return result;
			}
			
		});
		
		log.info("service insertMember2 : {}", result);
		
		return result;
	}

}

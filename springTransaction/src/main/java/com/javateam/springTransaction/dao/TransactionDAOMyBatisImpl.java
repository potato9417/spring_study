package com.javateam.springTransaction.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.springTransaction.domain.MemberVO;
import com.javateam.springTransaction.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Repository 
@Slf4j
public class TransactionDAOMyBatisImpl 
				implements TransactionDAOMyBatis {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertMember(final MemberVO member) {
		
		log.info("dao insertMember");
		
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		memberMapper.insertMember(member);
		
		// sqlSession.insert("com.javateam.springTransaction.mapper",member);
		
	} // insertMember

	@Override
	public MemberVO getMember(String id) {
		
		log.info("dao getMember");
		
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return memberMapper.getMember(id);
		
		// return sqlSession.selectOne("com.javateam.springTransaction.mapper" + ".MemberMapper.getMenber", id);
	} // 


	@Override
	public void updateMember(final MemberVO member) {
		
		log.info("dao updateMember");
		
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		memberMapper.updateMember(member);
		
	} //

	@Override
	public void deleteMember(final String id) {
		
		log.info("dao deleteMember");
				
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		memberMapper.deleteMember(id);
		
	} //

	@Override
	public List<MemberVO> getAllMembers() {
		
		log.info("dao getAllMembers");

		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return memberMapper.getAllMembers();
		
	} //
	
} // class 
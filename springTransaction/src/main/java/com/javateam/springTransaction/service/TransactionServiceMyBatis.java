package com.javateam.springTransaction.service;

import java.util.List;

import com.javateam.springTransaction.domain.MemberVO;

public interface TransactionServiceMyBatis {
	
	void insertMember(MemberVO member);
	MemberVO getMember(String id);
	void updateMember(MemberVO member);
	void deleteMember(String id);
	List<MemberVO> getAllMembers();
	
	
	// 추가) insert는 결과를 확인하기 어려워서 리턴값을 확실하게 알수있는 함수 추가 
	boolean insertMember2(MemberVO member);

}
/**
 * 
 */
package com.javateam.springTransaction.mapper;

import java.util.List;

import com.javateam.springTransaction.domain.MemberVO;

/**
 * @author javateam
 *
 */
public interface MemberMapper {
	
	void insertMember(MemberVO member);
	void updateMember(MemberVO member);
	List<MemberVO> getAllMembers();
	MemberVO getMember(String id);
	void deleteMember(String id);

}
package com.javateam.springParamProc.vo;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
// import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MemberDTO {
	
	private static final Logger log = LoggerFactory.getLogger(MemberDTO.class);
	
	private String eDriver;
	private String eeDriver;
	// 롬복으로 생성시시 geteDriver로 getter/setter를 생성하므로 문제 발생
	// => 직접 생성해야함 => 직접 생성한 코드를 인식
	// ex) eeDriver => getEeDriver / eDriver => geteDriver 
	private String name;
	private String eDriver2;
	
	
	public static void main(String[] args) {
		
		MemberDTO memberDTO = new MemberDTO();
		
//		MeberDTO.builder()
//				.name("Java")
//				.eDriver2("Oracle")
//				.eeDriver("h2")
//				.build();
		
		memberDTO.setEeDriver("oracleDriver");
		System.out.println(memberDTO.getEeDriver());
		
		log.info(memberDTO.getEeDriver());
		
	}

}

package com.javateam.myBatisTransactionSample.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employees {

	private int employeeId;
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private long salary;
	private int commissionPct;
	private int managerId;
	private int departmentId;

}
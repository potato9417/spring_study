/**
 * 
 */
package com.javateam.myBatisTransactionSample.domain;

import java.sql.Date;

//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;

/**
 * VO (Entity Bean) : hr.job_history
 * 
 * @author java
 *
 */
//@Getter
//@Setter
//@ToString
//@Data
public class JobHistory {
	
	/** 사원 번호 */
	private int employeeId;
	
	/** 업무 개시일 */
	private Date startDate;
	
	/** 업무 종료일 */
	private Date endDate;

	/** 직무 번호 */
	private String jobId;
	
	/** 부서 번호 */
	private int departmentId;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "JobHistory [employeeId=" + employeeId + ", startDate=" + startDate + ", endDate=" + endDate + ", jobId="
				+ jobId + ", departmentId=" + departmentId + "]";
	}
	
}

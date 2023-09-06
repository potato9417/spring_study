<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<table>
	 <tr>
		 <td width="50px" align="center">id</td>
		 <td align="center">name</td>
		 <td align="center">email</td>
		 <td align="center">phoneNumber</td>
		 <td align="center">hireDate</td>
		 <td align="center">jobId</td>
		 <td align="center">salary</td>
		 <td align="center">pct</td>
		 <td align="center">managerId</td>
		 <td align="center">departmentId</td>
	 </tr>

	 <c:forEach items="${employeesList}" var="employees">
		 <tr>
			 <td>${employees.employeeId}</td>
			 <td>${employees.firstName} ${employees.lastName}</td>
			 <td>${employees.email}</td>
			 <td>${employees.phoneNumber}</td>
			 <td>${employees.hireDate}</td>
			 <td>${employees.jobId}</td>
			 <td>${employees.salary}</td>
			 <td>${employees.commissionPct}</td>
			 <td>${employees.managerId }</td>
			 <td>${employees.departmentId }</td>	
		 </tr>
	 </c:forEach>

</table>

</body>
</html>

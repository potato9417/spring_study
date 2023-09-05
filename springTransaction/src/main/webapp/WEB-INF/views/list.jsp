<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	 
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>전체 회원 현황</title>
<style>
	* { font-size:9pt; } 
</style>
</head>
<body>

	<table id="members" border="1">
		
		<!-- title -->
		<tr>
			<td>번호</td>
			<td>아이디</td>
			<td>패쓰워드</td>
			<td>이 름</td>
			<td>주 소</td>
			<td>가입일</td>
		</tr>
		<!--// title -->
	
		<!-- content -->
		<c:forEach items="${members}" var="member" varStatus="st">
		<tr>
			<td>${st.count}</td>
			<td>${member.id}</td>
			<td>${member.pw}</td>
			<td>${member.name}</td>
			<td>${member.address}</td>
			<%-- <td><fmt:formatDate value="${member.joindate}" type="date" /></td> --%>
			<td><fmt:formatDate value="${member.joindate}" 
								type="date" 
								pattern="yyyy년 M월 d일"/></td>
			<%-- <td><fmt:parseDate var="tmp" value="${member.joindate}" 
                               pattern="yyyy-M-d"/>
                <fmt:formatDate value="${tmp}" 
                                pattern="yyyy년 M월 d일"/></td> --%>										
		</tr>
		
		</c:forEach>
		<!-- // content -->
		
	</table>
	
</body>
</html>
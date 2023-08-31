<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>form param transfer</title>
</head>
<body>
	
	<!-- RestController Test -->
	<!-- 이 부분에서 action 경로를 restAction, restAction2로 변경하면서 각 경로별로 테스트합니다. -->
	<form name="frm" 
		  method="post" 
		  action="${pageContext.request.contextPath}/restAction2">
		<input type="text" id="name" name="name"><br>
		<input type="text" id="grade" name="grade"><br>
		<input type="submit" value="전송">
	</form>
	
</body>
</html>
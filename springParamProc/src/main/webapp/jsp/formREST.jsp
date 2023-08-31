<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>form REST</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" charset="UTF-8"></script>
<script>
$(document).ready(function() {

	$("#btn").click(function(e){
		
		var name = $('#name').val();
		var grade = $("#grade").val();
       
         $.ajax ({
 
             url : '${pageContext.request.contextPath}/action3/name/'+name+"/grade/"+grade,
             type : 'get',
             dataType:'text',
             /* data : {
                 name : $('#name').val()
             }, */
             success : function(data) {
               
                console.log("data : "+data);
               	alert("인자 전송 성공 !");
                               
            }, // success
             
             error : function(xhr, status) {
                 
                console.log(xhr+" : "+status); // 에러 코드
            } //
 
    	}); // $.ajax
     
    }); // button
   
}); // ready
</script>
</head>
<body>

	이름 : <input type="text" id="name" /><br>
	학년 : <input type="text" id="grade" /><br>
	<input type="button" id="btn" value="전송" />

</body>
</html>
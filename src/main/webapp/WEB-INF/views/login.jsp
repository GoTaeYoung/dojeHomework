<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<style type="text/css">
.container {
	width: 400px;
	margin: 0 auto;
}

.input {
	width: 100%;
	height: 40px;
	padding: 5px;
	border-radius: 3px;
	margin-bottom: 10px;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div>
		<form method="post" class="container" action="main">
			<input type="hidden" name="page" value="login">
			<label>
				아이디 <br> 
				<input type="text" id="id" name="id" class="input" placeholder="아이디를 입력하세요">
			</label>
			<label>
				비밀번호 <br>
				<input type="password" id="pw" name="pw" class="input" placeholder="비밀번호를 입력하세요">
			</label>
			
			<input type="submit" onclick="return login()" value="로그인">
		</form>
	</div>
</body>
<script type="text/javascript">
	function login() {
		var id = $("#id").val();
		var pw = $("#pw").val();
		
		
		if (id == null || id == "") {
			alert("아이디를 입력하세요");
			return false;
		}
		
	 	if (pw == null || pw == "") {
			alert("비밀번호를 입력하세요");
			return false;
		}
	}
</script>
</html>
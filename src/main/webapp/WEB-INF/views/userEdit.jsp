<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>정보수정</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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

input[type="checkbox"],[type="radio"] {
	margin-left: 20px;
}

.snsDiv {
	width: 104%;
	margin-bottom: 5px;
}

.snsName,.snsAddr {
	text-align: center;
}

.snsAddr {
	width: 180px;
}

.defaultSns {
	width: 55%;
}
</style>
</head>
<body>
	<div>
		<form method="post" class="container" action="main">
			<input type="hidden" name="page" value="edit">
			<label>
				아이디 <br> 
				<input type="text" id="id" name="id" class="input" value="${user.id}" readonly="readonly">
			</label>
			<label>
				비밀번호 <br>
				<input type="password" id="pw" name="pw" class="input" value="${user.pw}" placeholder="새 비밀번호를 입력하세요" data-text="비밀번호">
			</label>
			<label>
				비밀번호 확인 <br>
				<input type="password" id="pwc" name="pwc" class="input" value="${user.pw}" placeholder="새 비밀번호를 다시 입력하세요" data-text="비밀번호 확인">
			</label>
			<label>
				이름 <br>
				<input type="text" id="name" name="name" class="input" value="${user.name}" placeholder="새 이름을 입력하세요" data-text="이름">
			</label>
			
			성별
			<div class="input">
				<label>
					<input type="radio" name="gender" value="남자"> 남자
				</label>
				<label>
					<input type="radio" name="gender" value="여자"> 여자
				</label>
			</div>
			
			취미
			<div class="input">
				<label>
					<input type="checkbox" name="hobby" value="축구"> 축구
				</label>
				<label>
					<input type="checkbox" name="hobby" value="농구"> 농구
				</label>
				<label>
					<input type="checkbox" name="hobby" value="배드민턴"> 배드민턴
				</label>
				<label>
					<input type="checkbox" name="hobby" value="독서"> 독서
				</label>
			</div>
			
			SNS<br> 
			<div id="snsContainer" class="container"></div>
			<input type="button" id="addSns" class="snsDiv" value="추가">
			<input type="hidden" id="sns" name="sns">
			
			<input type="submit" class="snsDiv" onclick="return edit()" value="수정하기">
			
			<input type="hidden" id="hGender" value="${user.gender}"> 
			<input type="hidden" id="hHobby" value="${user.hobby}"> 
			<input type="hidden" id="hSns" value=${user.sns}>
		</form>
	</div>
</body>
<script type="text/javascript">
	var gender = $("#hGender").val();
	var hobby = $("#hHobby").val();
	var sns = JSON.parse($("#hSns").val());
	var snsNum = 0;
	
	$(function() {
		hobby = hobby.replace(']', '');
		hobby = hobby.replace('[', '');
		hobby = hobby.replace(/ /g, '');
		
		var array = hobby.split(',');
		
		$("input[name=hobby]").each(function() {
			for (var i = 0; i < array.length; i++)
				if ($(this).val() == array[i])
					$(this).attr('checked', true);
		});
		
		$('input[name=gender]').each(function() {
			if ($(this).val() == gender) {
				$(this).attr('checked', true);
			}
		});
		
		$.each(sns, function(key, value){
			addSns();		
			$(".snsName").eq(snsNum).val(key);
			$(".snsAddr").eq(snsNum).val(value);
			snsNum += 1;
		});
	});
//--------------------------------------------
	$('#addSns').click(function() {
		addSns();
	});
//---------------------------------------------------------
	$(document).on("click", ".removeSns", function() {
		$(this).parent().remove();
	});
//--------------------------------------------------------------
	function edit() {
		var array = [ "#id", "#pw", "#pwc", "#name" ];
		var arr = "";
	//-------------------------------------------------------------
		for (var i = 0; i < array.length; i++) {
			arr = array[i];
			if ($(arr).val() == "") {
				alert($(arr).data("text") + " 입력하세요");
				$(arr).focus();
				return false;
				break;
			}
		}
	//-----------------------------------------------------------	
		if ($("input[name=gender]:checked").length == 0) {
			alert("성별 체크");
			$("input[name=gender]").first().focus();
			return false;
		}
	//--------------------------------------------------------		
		if ($('#pw').val() != $('#pwc').val()) {
			alert("비밀번호가 다릅니다");
			return false;
		}
	//----------------------------------------------
		createSnsJson();
	}
//-----------------------------------------------------------------------------------------------------
	function addSns(){
		var addStr = "" +
			"<div class='snsDiv'>" +
			"	<input type='text' class='snsName'> : <input type='text' class='snsAddr'> <input type='button' class='removeSns' value='삭제'>" +
			"</div>";
		$("#snsContainer").append(addStr);
	}
//------------------------------------------------------------------------------------------
	function createSnsJson(){
		var jsonOb = new Object();
		var jsonKey = "";
		var jsonValue = "";
		
		$(".snsName").each(function(i) {
			jsonKey = $(this).val();
			jsonValue =  $(".snsAddr").eq(i).val();
			if (jsonKey != "" &&  jsonValue != "") {
				jsonOb[jsonKey] = jsonValue;
			}
		});
		
		var jsonStr = JSON.stringify(jsonOb);
		$("#sns").val(jsonStr);
	}
</script>
</html>
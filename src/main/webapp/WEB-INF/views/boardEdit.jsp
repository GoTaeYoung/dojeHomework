<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정</title>
<style type="text/css">
* {
	margin: auto;
}

#form {
	width: 800px;
}
label {
	width: 100%;
}
#title {
	width: 40%;
	height: 30px;
	margin: 10px;
}

#writer {
	width: 20%;
	height: 30px;
}

#content {
	width: 95%;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/resource/editor/js/HuskyEZCreator.js"></script>
</head>
<body>
	<div>
		<form method="post" action="main" enctype="multipart/form-data" id="form">
			<input type="hidden" id="htype" name="type" value="${board.type}">
			<input type="hidden" id="page" name="page" value="boardEdit">
			<h1>
				<c:choose>
					<c:when test="${board.type eq 'free'}">
			        	자유 게시판 수정
			    	</c:when>
					<c:when test="${board.type eq 'notice'}">
			        	공지사항 수정
			    	</c:when>
			    	<c:when test="${board.type eq 'image'}">
			        	이미지 게시판 수정
			    	</c:when>
			    	<c:when test="${board.type eq 'guest'}">
			        	방명록 수정
			    	</c:when>
				</c:choose>
			</h1>
			<label>
				<input type="text" id="no" name="no" value="${board.no }" readonly="readonly">
				<input type="text" id="title" name="title" placeholder="제목을 입력하세요" value="${board.title }">
			</label>
			<label>
				작성자
				<input type="text" id="writer" name="writer" value="${user.id}" readonly="readonly">
			</label>
			<label>
				<textarea id="content" name="content" rows="30" placeholder="내용을 입력하세요"></textarea>
			</label>
			<label>
				등록일
				<input type="text" name="date" value="${board.date}" readonly="readonly">
			</label>
			<div id="type"></div>
	
			<input type="button" id="savebutton" value="글 등록">
		</form>
	</div>
</body>
<script>
	//전역변수선언
	var editor_object = [];
	
	loadType();
	
	$(function() {
		// nhn.husky.EZCreator.createInIFrame 로 스마트에디터를 iframe을 이용하여 화면에 띄움, textarea와 패키지개념이긴 하나 실질적으로 별개의 프레임
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : editor_object,
			elPlaceHolder : "content", // elPlaceHolder 는 스마트에디터를 연결할 textarea의 id명을 쓴다
			sSkinURI : "/resource/editor/SmartEditor2Skin.html", // sSkinURI 는 에디터스킨 html파일 경로를 지정
			htParams : {
				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : false,
				// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : false
			},
			fOnAppLoad : function(){
				editor_object.getById["content"].exec("PASTE_HTML", ['${board.content}']);
			},
			fCreator : "createSEditor2"
		});
		// editor_object 배열변수를 이용하여 smarteditor id 값을 가진 textarea 폼에 에디터스킨에서 입력한 html내용들을 대입
		$("#savebutton").click(function() {
			//id가 content인 textarea에 에디터에서 대입
			editor_object.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
		
			$(".image").last().remove();

			$("#form").submit();
		});
	});
	function loadType() {
		var type = $("#htype").val();
		if(type == "free") {
			$('#type').html(""
				+ "<label>"
				+ "<input type='radio' name='open' value='true'> 글 공개"
				+ "</label>"
				+ "<label>"
				+ "<input type='radio' name='open' value='false'> 글 비공개"
				+ "</label>");
			$(window).load(function() {
				$('input[name=open]').each(function() {
					if ($(this).val() == "${free.open}") {
						$(this).prop('checked', true);
					}
				});
			});
		}
		
		else if(type == "image") {
			$("#type").html(""
				+ "<input type='hidden' name='images_name' value='image'>"
				+ "<input type='hidden' name='total' value='0'>"
				+ "<input type='file' class='image' name='image' onchange='showImage(this)'>"
				+ "<img id='show0'><br>");
		}
		else if(type == "notice") {
			$("#type").html(""
				+ "<label>"
				+ "수정일 "
				+ "<input type='text' name='edit_date' value='${notice.edit_date}' readonly='readonly'>"
				+ "</label>");
		}
	}
	var cnt = 0;
	function showImage(img) {
		if (img.files && img.files[0]) {
			cnt = cnt + 1;
			$("input[name=total]").val(cnt);
			var reader = new FileReader();
			
			reader.onload = function(e) {
				$('#show' + cnt).attr('src', e.target.result).width(300).height(300);

			}
			$("#type").append(""
				+ "<input type='file' class='image' name='image' onchange='showImage(this)'>"
				+ "<img id='show"+ cnt +"'><br>")

			reader.readAsDataURL(img.files[0]);
		}
	}
</script>
</html>
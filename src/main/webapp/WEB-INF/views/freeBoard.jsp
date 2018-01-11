<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자유 게시판</title>
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
	width: 60%;
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
		<form method="post" action="main" enctype="multipart/form-data"
			id="form">
			<input type="hidden" id="type" name="type" value="free"> <input
				type="hidden" id="page" name="page" value="free"> <label>
				제목 <input type="text" id="title" name="title"
				placeholder="제목을 입력하세요">
			</label> <label> 작성자 <input type="text" id="writer" name="writer"
				value="${user.id}" readonly="readonly">
			</label> <label> <textarea id="content" name="content" rows="30"
					placeholder="내용을 입력하세요"></textarea>
			</label>

			<div>
				<label> <input type="radio" name="open" value="true">
					글 공개
				</label> <label> <input type="radio" name="open" value="false">
					글 비공개
				</label>
			</div>

			<input type="button" id="savebutton" value="글 등록">
		</form>
	</div>
</body>
<script>
	//전역변수선언
	var editor_object = [];

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
			fCreator : "createSEditor2"
		});

		// editor_object 배열변수를 이용하여 smarteditor id 값을 가진 textarea 폼에 에디터스킨에서 입력한 html내용들을 대입
		$("#savebutton").click(function() {
			//id가 content인 textarea에 에디터에서 대입
			editor_object.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

			if ($("input[name=open]:checked").length == 0) {
				alert("글 공개 여부 체크");
				$("input[name=open]").first().focus();
				return false;
			}

			$("#form").submit();
		});
	});
</script>
</html>
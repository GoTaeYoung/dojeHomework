<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인 페이지</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style type="text/css">
* {
	margin: 0 auto;
	padding: 0;
	list-style: none;
	text-decoration: none;
	text-align: center;
}
</style>
</head>
<body>
	<h1>환영합니다 ${user.name} 님</h1>
	<form id="form" method="post">
		<input type="button" onclick="go(1)" value="정보 수정"> <input
			type="button" onclick="go(2)" value="공지사항"> <input
			type="button" onclick="go(3)" value="자유 게시판"> <input
			type="button" onclick="go(4)" value="이미지 게시판"> <input
			type="button" onclick="go(5)" value="방명록"> <input
			type="button" onclick="go(6)" value="글 수정"> <input
			type="button" onclick="go(7)" value="로그아웃"> <input
			type="button" onclick="go(8)" value="회원가입"> <br> 번호
		<p>${board.no }</p>
		제목
		<p>${board.title }</p>
		내용
		<p>${board.content }</p>
		작성일
		<p>${board.date }</p>
		타입
		<p>${board.type }</p>
		<c:choose>
			<c:when test="${board.type eq 'free'}">
		        	공개 여부 <p>${free.open}</p>
			</c:when>
			<c:when test="${board.type eq 'notice'}">
			       	수정일 <p>${notice.edit_date }수정자
				<p>${notice.edit_id }
			</c:when>
			<c:when test="${board.type eq 'image'}">
			       	이미지 개수 <p>${image.total}</p>
				<c:forEach var='img' items='${list}'>
					<img alt='no' src='${img.url}'
						style='width: 100%; max-width: 1000px'>
					<br>
				</c:forEach>
			</c:when>
		</c:choose>
	</form>
</body>
<script>
	function go(i) {
		// 정보수정
		if (i == 1) {
			$('#form').attr('action', 'userEdit').submit();
		}

		// 공지사항
		else if (i == 2) {
			$('#form').attr('action', 'noticeBoard').submit();
		}

		// 자유게시판
		else if (i == 3) {
			$('#form').attr('action', 'freeBoard').submit();
		}

		// 이미지 게시판
		else if (i == 4) {
			$('#form').attr('action', 'imageBoard').submit();
		}

		// 방명록
		else if (i == 5) {
			$('#form').attr('action', 'guestBoard').submit();
		}

		// 글 수정
		else if (i == 6) {
			$('#form').attr('action', 'boardEdit').submit();
		}

		// 로그아웃
		else if (i == 7) {
			$('#form').attr('action', 'login').submit();
		}

		// 회원가입
		else if (i == 8) {
			$('#form').attr('action', 'userJoin').submit();
		}
	}
</script>
</html>
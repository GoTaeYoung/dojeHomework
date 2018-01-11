package com.doje.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doje.dao.DojeDao;
import com.doje.vo.BoardVo;
import com.doje.vo.FreeBoardVo;
import com.doje.vo.GuestBoardVo;
import com.doje.vo.ImageBoardVo;
import com.doje.vo.ImageListVo;
import com.doje.vo.NoticeBoardVo;
import com.doje.vo.UserVo;

@Service
public class DojeService {
	@Autowired
	DojeDao dojeDao;

	public String checkId(HttpServletRequest req) {
		return dojeDao.checkId(req.getParameter("id")) == 0 ? "사용 가능" : "사용 불가능";
	}

	public void insertUser(UserVo userVo) {
		dojeDao.insertUser(userVo);
		System.out.println("가입 성공\n" + userVo.toString());
	}

	public void updateUser(UserVo userVo) {
		dojeDao.updateUser(userVo);
		System.out.println("수정 성공\n" + userVo.toString());
	}

	public UserVo selectUser(HttpServletRequest req) {
		return dojeDao.selectUser(req.getParameter("id"));
	}

	public String login(HttpServletRequest req, UserVo userVo) {
		if (userVo == null || !userVo.getPw().equals(req.getParameter("pw"))) {
			System.out.println("로그인 실패");
			return "/login";
		}
		System.out.println("로그인 성공\n" + userVo.toString());
		req.getSession().setAttribute("user", userVo);
		return "/main";
	}

	public String insertBoard(BoardVo boardVo) {
		dojeDao.insertBoard(boardVo);
		return boardVo.getNo();
	}

	public BoardVo selectBoard(String no) {
		return dojeDao.selectBoard(no);
	}

	public void updateBoard(BoardVo boardVo) {
		dojeDao.updateBoard(boardVo);
	}

	public void updateFreeBoard(FreeBoardVo freeBoardVo) {
		dojeDao.updateFreeBoard(freeBoardVo);
	}

	public void updateNoticeBoard(NoticeBoardVo noticeBoardVo) {
		dojeDao.updateNoticeBoard(noticeBoardVo);
	}

	public void updateImageBoard(ImageBoardVo imageBoardVo) {
		dojeDao.updateImageBoard(imageBoardVo);
	}

	public FreeBoardVo freeBoardQuery(String board_pk, FreeBoardVo freeBoardVo) {
		freeBoardVo.setBoard_pk(board_pk);
		dojeDao.insertFreeBoard(freeBoardVo);
		freeBoardVo = dojeDao.selectFreeBoard(board_pk);
		System.out.println(freeBoardVo.toString() + "\n자유 게시판 등록 성공");
		return freeBoardVo;
	}

	public NoticeBoardVo noticeBoardQuery(String board_pk, NoticeBoardVo noticeBoardVo) {
		noticeBoardVo.setBoard_pk(board_pk);
		dojeDao.insertNoticeBoard(noticeBoardVo);
		noticeBoardVo = dojeDao.selectNoticeBoard(board_pk);
		System.out.println(noticeBoardVo.toString() + "\n공지사항 등록 성공");
		return noticeBoardVo;
	}

	public ImageBoardVo imageBoardQuery(String board_pk, ImageBoardVo imageBoardVo) {
		imageBoardVo.setBoard_pk(board_pk);
		dojeDao.insertImageBoard(imageBoardVo);
		imageBoardVo = dojeDao.selectImageBoard(board_pk);
		System.out.println(imageBoardVo.toString() + "\n이미지 게시판 등록 성공");
		return imageBoardVo;

	}

	public GuestBoardVo guestBoardQuery(String board_pk, GuestBoardVo guestBoardVo) {
		guestBoardVo.setBoard_pk(board_pk);
		dojeDao.insertGuestBoard(guestBoardVo);
		guestBoardVo = dojeDao.selectGuestBoard(board_pk);
		System.out.println(guestBoardVo.toString() + "\n방명록 등록 성공");
		return guestBoardVo;
	}

	public void insertImageList(ImageListVo imageListVo) {
		dojeDao.insertImageList(imageListVo);
		System.out.println(imageListVo.toString());
	}

	public List<ImageListVo> selectImageList(String image_pk) {
		return dojeDao.selectImageList(image_pk);
	}

	public void deleteImageList(String no) {
		dojeDao.deleteImageList(no);
	}

	public ImageBoardVo selectImageBoard(String board_pk) {
		return dojeDao.selectImageBoard(board_pk);
	}
}
package com.doje.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.doje.vo.BoardVo;
import com.doje.vo.FreeBoardVo;
import com.doje.vo.GuestBoardVo;
import com.doje.vo.ImageBoardVo;
import com.doje.vo.ImageListVo;
import com.doje.vo.NoticeBoardVo;
import com.doje.vo.UserVo;

@Mapper
public interface DojeDao {
	public int checkId(String id);

	// ---------------------------------------------------------
	public void insertUser(UserVo userVo);

	public void updateUser(UserVo userVo);

	public UserVo selectUser(String id);

	// ----------------------------------------------------------
	public void insertBoard(BoardVo boardVo);

	public void updateBoard(BoardVo boardVo);

	public BoardVo selectBoard(String no);

	// ----------------------------------------------------------
	public void insertFreeBoard(FreeBoardVo freeBoardVo);

	public void updateFreeBoard(FreeBoardVo freeBoardVo);

	public FreeBoardVo selectFreeBoard(String board_pk);

	// ----------------------------------------------------------
	public void insertNoticeBoard(NoticeBoardVo noticeBoardVo);

	public void updateNoticeBoard(NoticeBoardVo noticeBoardVo);

	public NoticeBoardVo selectNoticeBoard(String board_pk);

	// ----------------------------------------------------------
	public void insertGuestBoard(GuestBoardVo guestBoardVo);

	public GuestBoardVo selectGuestBoard(String board_pk);

	// ----------------------------------------------------------
	public void insertImageBoard(ImageBoardVo imageBoardVo);

	public void updateImageBoard(ImageBoardVo imageBoardVo);

	public ImageBoardVo selectImageBoard(String board_pk);

	// ----------------------------------------------------------
	public void insertImageList(ImageListVo imageListVo);

	public void deleteImageList(String no);

	public List<ImageListVo> selectImageList(String image_pk);
}

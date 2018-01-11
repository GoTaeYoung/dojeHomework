package com.doje.vo;

public class GuestBoardVo {
	String no;
	String board_pk;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getBoard_pk() {
		return board_pk;
	}

	public void setBoard_pk(String board_pk) {
		this.board_pk = board_pk;
	}

	@Override
	public String toString() {
		return "GuestBoardVo [no=" + no + ", board_pk=" + board_pk + "]";
	}

}

package com.doje.vo;

public class FreeBoardVo {
	String no;
	String board_pk;
	String open;

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

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	@Override
	public String toString() {
		return "FreeBoardVo [no=" + no + ", board_pk=" + board_pk + ", open=" + open + "]";
	}

}

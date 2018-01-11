package com.doje.vo;

public class ImageBoardVo {
	String no;
	String board_pk;
	String total;

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

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ImageVo [no=" + no + ", board_pk=" + board_pk + ", total=" + total + "]";
	}
}

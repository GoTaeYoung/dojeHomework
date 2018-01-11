package com.doje.vo;

public class NoticeBoardVo {
	String no;
	String board_pk;
	String edit_date;
	String edit_id;

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

	public String getEdit_date() {
		return edit_date;
	}

	public void setEdit_date(String edit_date) {
		this.edit_date = edit_date;
	}

	public String getEdit_id() {
		return edit_id;
	}

	public void setEdit_id(String edit_id) {
		this.edit_id = edit_id;
	}

	@Override
	public String toString() {
		return "NoticeBoardVo [no=" + no + ", board_pk=" + board_pk + ", edit_date=" + edit_date + ", edit_id="
				+ edit_id + "]";
	}
}

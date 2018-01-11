package com.doje.vo;

public class UserVo {
	String id;
	String pw;
	String name;
	String gender;
	String hobby;
	String sns;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getSns() {
		return sns;
	}

	public void setSns(String sns) {
		this.sns = sns;
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", hobby=" + hobby
				+ ", sns=" + sns + "]";
	}
}

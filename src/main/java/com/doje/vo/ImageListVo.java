package com.doje.vo;

public class ImageListVo {
	String no;
	String image_pk;
	String url;
	String origin_name;
	String server_name;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getImage_pk() {
		return image_pk;
	}

	public void setImage_pk(String image_pk) {
		this.image_pk = image_pk;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOrigin_name() {
		return origin_name;
	}

	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}

	public String getServer_name() {
		return server_name;
	}

	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}

	@Override
	public String toString() {
		return "ImageListVo [no=" + no + ", image_pk=" + image_pk + ", url=" + url + ", origin_name=" + origin_name
				+ ", server_name=" + server_name + "]";
	}
}

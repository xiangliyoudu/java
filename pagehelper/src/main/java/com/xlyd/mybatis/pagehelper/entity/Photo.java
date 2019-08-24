package com.xlyd.mybatis.pagehelper.entity;

import java.io.Serializable;

public class Photo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String name;
	private byte[] pic;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
	

}

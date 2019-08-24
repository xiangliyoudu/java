package com.xlyd.vue.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cuser")
public class User {
	
	private String name;
	
	private String position;

	private boolean show;
	

	public User() {
		super();
	}

	public User(String name, String position, boolean show) {
		super();
		this.name = name;
		this.position = position;
		this.show = show;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

}

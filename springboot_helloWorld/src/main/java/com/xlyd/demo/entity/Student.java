package com.xlyd.demo.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
// 绑定配置文件中的值, 优先级高，也可通过value逐一注入，可互补，
@ConfigurationProperties(prefix = "student")
// 引入非默认的配置文件
@PropertySource(value={"classpath:conf.properties"})
public class Student {
//	@Value("kite")
	private String name;
	@Value("35")
	private int age;
	private boolean gender;
	private Date birthday;
	private Map<String, Object> location;
	private String[] hobbies;
	private List<String> skills;
	private Pet pet;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", gender=" + gender + ", birthday=" + birthday
				+ ", location=" + location + ", hobbies=" + Arrays.toString(hobbies) + ", skills=" + skills + ", pet="
				+ pet + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Map<String, Object> getLocation() {
		return location;
	}

	public void setLocation(Map<String, Object> location) {
		this.location = location;
	}

	public String[] getHobbies() {
		return hobbies;
	}

	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

}

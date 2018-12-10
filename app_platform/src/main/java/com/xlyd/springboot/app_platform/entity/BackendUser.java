package com.xlyd.springboot.app_platform.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BackendUser {
	private Integer id;//主键id
	private String userCode;//用户编码（登录账号）
	private String userName;//用户名称
	private String userPassword;//用户密码
	private Integer userType;//用户角色类型id
	private Integer createdBy;//创建者
	private Date creationDate;//创建时间
	private Integer modifyBy;//更新者
	private Date modifyDate;//更新时间
	private String userTypeName;//用户角色类型名称
}

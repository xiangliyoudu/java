package cn.service;

import org.apache.ibatis.annotations.Param;

import cn.pojo.DevUser;

public interface IDevUserService {
	
	public DevUser findByNameAndPwd(@Param("userCode") String userCode,
			@Param("userPassword") String userPassword);

}

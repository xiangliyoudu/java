package com.xlyd.springboot.app_platform.service;

import org.apache.ibatis.annotations.Param;

import com.xlyd.springboot.app_platform.entity.DevUser;

public interface IDevUserService {
	
	public DevUser findByNameAndPwd(@Param("userCode") String userCode,
                                    @Param("userPassword") String userPassword);

}

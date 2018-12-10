package com.xlyd.springboot.app_platform.service;

import org.apache.ibatis.annotations.Param;

import com.xlyd.springboot.app_platform.entity.BackendUser;


public interface IBackendUserService {
	/**
	 * 根据用户编码和密码查询：BackendUser
	 */
	public BackendUser findByNameAndPwd(@Param("userCode") String userCode,
                                        @Param("userPassword") String userPassword);
	
}

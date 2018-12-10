package cn.service;

import org.apache.ibatis.annotations.Param;

import cn.pojo.BackendUser;


public interface IBackendUserService {
	/**
	 * 根据用户编码和密码查询：BackendUser
	 */
	public BackendUser findByNameAndPwd(@Param("userCode")String userCode,
										@Param("userPassword")String userPassword);
	
}

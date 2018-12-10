/*
 * Powered By [rapid-framework]
 * Web Site: http://blog.csdn.net/houfeng30920/article/details/52730893
 * Csdn Code: 
 * Since 2015 - 2017
 */

package cn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.pojo.DevUser;

public interface IDevUserDao {

	/**
	 * 创建DevUser
	 **/
	public void save(DevUser devUser);

	/**
	 * 更新DevUser
	 **/
	public void update(DevUser devUser);

	/**
	 * 删除DevUser
	 **/
	public void deleteById(java.lang.Long id);

	/**
	 * 根据ID查询DevUser
	 **/
	public DevUser findById(java.lang.Long id);

	/**
	 * 查询: DevUser
	 **/
	public List<DevUser> findAll(Map<String, Object> paraMap);
	
	public DevUser findByNameAndPwd(@Param("userCode") String userCode,
									@Param("userPassword") String userPassword);

}

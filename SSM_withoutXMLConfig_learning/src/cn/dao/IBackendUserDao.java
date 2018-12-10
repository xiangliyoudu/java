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

import cn.pojo.BackendUser;

/**
 *
 * @author hou
 * @version 1.0
 * @since 1.0
 * */
public interface IBackendUserDao {

	/**
	 * 创建BackendUser
	 **/
	public void save(BackendUser backendUser);

	/**
	 * 更新BackendUser
	 **/
	public void update(BackendUser backendUser);

	/**
	 * 删除BackendUser
	 **/
	public void deleteById(java.lang.Long id);

	/**
	 * 根据ID查询BackendUser
	 **/
	public BackendUser findById(java.lang.Long id);

	/**
	 * 查询: BackendUser
	 **/
	public List<BackendUser> findAll(Map<String, Object> paraMap);
	
	/**
	 * 根据用户编码和密码查询：BackendUser
	 */
	public BackendUser findByNameAndPwd(@Param("userCode") String userCode,
										@Param("userPassword") String userPassword);
		
	
}

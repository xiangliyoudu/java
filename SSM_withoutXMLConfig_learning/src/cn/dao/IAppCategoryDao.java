/*
 * Powered By [rapid-framework]
 * Web Site: http://blog.csdn.net/houfeng30920/article/details/52730893
 * Csdn Code: 
 * Since 2015 - 2017
 */

package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.AppCategory;

/**
 *
 * @author hou
 * @version 1.0
 * @since 1.0
 * */
public interface IAppCategoryDao {

	/**
	 * 创建AppCategory
	 **/
	public void save(AppCategory appCategory);

	/**
	 * 更新AppCategory
	 **/
	public void update(AppCategory appCategory);

	/**
	 * 删除AppCategory
	 **/
	public void deleteById(java.lang.Long id);

	/**
	 * 根据ID查询AppCategory
	 **/
	public AppCategory findById(java.lang.Long id);

	/**
	 * 查询: AppCategory
	 **/
	public List<AppCategory> findAll();
	
	/**
	 * @return: 查询一级分类列表
	 */
	public List<AppCategory> findLevel1();
	
	/**
	 * @return: 查询二级、三级分类列表
	 */
	public List<AppCategory> findLevel23(@Param("parentId") Integer pid);
	


}

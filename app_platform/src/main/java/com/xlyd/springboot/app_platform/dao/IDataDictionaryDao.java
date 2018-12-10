/*
 * Powered By [rapid-framework]
 * Web Site: http://blog.csdn.net/houfeng30920/article/details/52730893
 * Csdn Code: 
 * Since 2015 - 2017
 */

package com.xlyd.springboot.app_platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xlyd.springboot.app_platform.entity.DataDictionary;

/**
 *
 * @author hou
 * @version 1.0
 * @since 1.0
 * */
public interface IDataDictionaryDao {

	/**
	 * 创建DataDictionary
	 **/
	public void save(DataDictionary dataDictionary);

	/**
	 * 更新DataDictionary
	 **/
	public void update(DataDictionary dataDictionary);

	/**
	 * 删除DataDictionary
	 **/
	public void deleteById(Long id);

	/**
	 * 根据ID查询DataDictionary
	 **/
	public DataDictionary findById(Long id);

	/**
	 * 查询: DataDictionary
	 **/
	public List<DataDictionary> findAll();
	
	
	/**
	 * @return:DataDictionary中应用平台列表
	 */
	public List<DataDictionary> findListByTypeCode(@Param("typeCode") String typeCode);

}

/*
 * Powered By [rapid-framework]
 * Web Site: http://blog.csdn.net/houfeng30920/article/details/52730893
 * Csdn Code: 
 * Since 2015 - 2017
 */

package com.xlyd.springboot.app_platform.dao;

import com.xlyd.springboot.app_platform.entity.AppInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IAppInfoDao {

	/**
	 * 添加AppInfo
	 **/
	public int addAppInfo(AppInfo appInfo);

	/**
	 * 更新AppInfo
	 **/
	public int update(AppInfo appInfo);

	/**
	 * 删除AppInfo
	 **/
	public int deleteById(@Param("id") Integer id);

	/**
	 * 查询所有待审核记录: AppInfo
	 **/
	public List<AppInfo> findAll(Map<String, Object> map);
	
	/**
	 * 查询所有待审核记录数
	 * @return
	 */
	public Integer appCount(Map<String, Object> map);

	/**
	 * 通过id查询app记录
	 * @return
	 */
	public AppInfo findById(@Param("id") Integer aid);
	
	/**跟新appinfo状态
	 * @param id
	 * @param status
	 * @return 
	 */
	public int updateStatus(@Param("modifyBy") Integer modifyBy,
                            @Param("modifyDate") Date modifyDate,
                            @Param("id") Integer id,
                            @Param("status") Integer status);
		
	/**功过apkname查询记录
	 * @param apkName
	 * @return
	 */
	public AppInfo findByAPKName(@Param("APKName") String apkName);
	
	
	/**跟新appinfo版本状态
	 * @return
	 */
	public int updateVersionId(@Param("modifyBy") Integer modifyBy,
                               @Param("modifyDate") Date modifyDate,
                               @Param("id") Integer id,
                               @Param("versionId") Integer versionId);
	
	/**update logoPicPath
	 * @param modifyBy
	 * @param modifyDate
	 * @param vid
	 * @return
	 */
	public Boolean emptyLogoPicPath(@Param("modifyBy") Integer modifyBy,
                                    @Param("modifyDate") Date modifyDate,
                                    @Param("id") Integer vid);

}

/*
 * Powered By [rapid-framework]
 * Web Site: http://blog.csdn.net/houfeng30920/article/details/52730893
 * Csdn Code: 
 * Since 2015 - 2017
 */

package cn.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.AppVersion;

public interface IAppVersionDao {

	/**
	 * 创建AppVersion
	 **/
	public int save(AppVersion appVersion);
	
	/**通过id删除记录
	 * @return
	 */
	public int deleteById(@Param("id") Integer id);

	/**
	 * 更新AppVersion
	 **/
	public int update(AppVersion appVersion);

	/**
	 * 根据ID查询AppVersion
	 **/
	public AppVersion findById(@Param("id") Integer vid);

	/**
	 * 查询: AppVersion
	 **/
	public List<AppVersion> findAll();
	
	/**根据appInfo的id查询其所有appversion列表
	 * @param id
	 * @return
	 */
	public List<AppVersion> findByAppInfoId(@Param("appInfoId")Integer id);

	
	/**清空apkfilename
	 * @param vid
	 * @return
	 */
	public Boolean emptyAPKFileName(@Param("modifyBy") Integer modifyBy,
									@Param("modifyDate") Date modifyDate,
									@Param("id") Integer vid);
}

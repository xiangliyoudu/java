package com.xlyd.springboot.app_platform.service.backend;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xlyd.springboot.app_platform.entity.AppVersion;

public interface IAppVersionService {
	/**
	 * 根据ID查询AppVersion
	 **/
	public AppVersion findById(@Param("id") Integer vid);
	
	/**根据appInfo的id查询其所有appversion列表
	 * @param id
	 * @return
	 */
	public List<AppVersion> findByAppInfoId(@Param("appInfoId") Integer id);

	/**
	 * 创建AppVersion
	 **/
	public int save(AppVersion appVersion);
	
	/**
	 * 更新AppVersion
	 **/
	public int update(AppVersion appVersion);
	
	/**清空apkfilename
	 * @param vid
	 * @return
	 */
	public Boolean emptyAPKFileName(@Param("modifyBy") Integer modifyBy,
                                    @Param("modifyDate") Date modifyDate,
                                    @Param("id") Integer vid);
	
	
	/**通过id删除记录
	 * @return
	 */
	public int deleteById(@Param("id") Integer id);
	
}

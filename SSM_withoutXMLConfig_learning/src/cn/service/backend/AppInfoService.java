package cn.service.backend;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.pojo.AppInfo;

public interface AppInfoService {
	/**
	 * 查询所有待审核记录: AppInfo
	 **/
	public List<AppInfo> findAll(Map<String, Object> map);

	/**
	 * 查询所有待审核记录数
	 * 
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
			@Param("status") Integer status );
	
	/**功过apkname查询记录
	 * @param apkName
	 * @return
	 */
	public AppInfo findByAPKName(@Param("APKName") String apkName);
	
	/**
	 * 添加AppInfo
	 **/
	public int addAppInfo(AppInfo appInfo);
	
	/**跟新appinfo版本状态
	 * @return
	 */
	public int updateVersionId(@Param("modifyBy") Integer modifyBy,
			@Param("modifyDate") Date modifyDate,
			@Param("id") Integer id, 
			@Param("versionId") Integer versionId);
	
	public Boolean emptyLogoPicPath(@Param("modifyBy") Integer modifyBy,
			@Param("modifyDate") Date modifyDate,
			@Param("id") Integer vid);
	
	/**
	 * 更新AppInfo
	 **/
	public int update(AppInfo appInfo);
	
	
	public int deleteById(Integer id);
	
}

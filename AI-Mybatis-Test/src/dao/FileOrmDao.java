package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.Dept;
import pojo.DeptFiles;
import pojo.FileOrm;
import pojo.User;
import pojo.UserFiles;

public interface FileOrmDao {

	// List<User> hdfsHaveUserAndNot(int fid);
	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:17:23
	 * @Description: 用户是否含有查看hdfs数据权限
	 * @param @param fid
	 * @param @param idtree
	 * @param @param userid
	 * @param @return
	 * @return List<User>
	 * @throws
	 */
	List<User> hdfsHaveUserAndNot(@Param("fileId") int fid, @Param("idtree") String idtree, 
								  @Param("userId") int userid);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:22:29
	 * @Description: 部门是否含有查看hdfs数据权限
	 * @param @param fid
	 * @param @return
	 * @return List<Dept>
	 * @throws
	 */
	List<Dept> hdfsHaveDeptAndNot(@Param("fileId") int fid);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:22:57
	 * @Description: 通过id物理删除hdfs数据
	 * @param @param id
	 * @return void
	 * @throws
	 */
	void delete(@Param("id") int id);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:24:04
	 * @Description: 通过name物理删除hdfs数据
	 * @param @param name
	 * @return void
	 * @throws
	 */
	void deleteByName(@Param("name") String name);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:27:26
	 * @Description: 通过fid删除用户对应的hdfs数据
	 * @param @param fid
	 * @return void
	 * @throws
	 */
	void deleteUserByfid(@Param("fileId") int fid);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:31:25
	 * @Description: 通过fid删除部门对应的hdfs数据
	 * @param @param fid
	 * @return void
	 * @throws
	 */
	void deleteDeptByfid(@Param("fileId") int fid);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:32:26
	 * @Description: 保存hdfs数据
	 * @param @param dept
	 * @return void
	 * @throws
	 */
	void save(FileOrm dept);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:33:25
	 * @Description: 通过id查询hdfs数据信息
	 * @param @param id
	 * @param @return
	 * @return FileOrm
	 * @throws
	 */
	FileOrm queryByID(@Param("id") int id);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:35:12
	 * @Description: 通过name查询hdfs数据信息
	 * @param @param name
	 * @param @return
	 * @return FileOrm
	 * @throws
	 */
	FileOrm queryByName(@Param("name") String name);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:36:07
	 * @Description: 通过uid删除用户对应的hdfs数据
	 * @param @param uid
	 * @return void
	 * @throws
	 */
	void deleteUserByUid(@Param("userId") int uid);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:37:09
	 * @Description: 通过did删除bdtm_dept_files表中的对应关系
	 * @param @param did
	 * @return void
	 * @throws
	 */
	void deleteDeptByDid(@Param("deptId") int did);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:43:35
	 * @Description: 更新hdfs数据信息
	 * @param @param fileOrm
	 * @return void
	 * @throws
	 */
	void update(FileOrm fileOrm);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:44:22
	 * @Description: 通过uid和fid,添加bdtm_user_files表中对应关系
	 * @param @param uid
	 * @param @param fid
	 * @return void
	 * @throws
	 */
	void saveUserFile(@Param("userId") int uid, @Param("fileId") int fid);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:47:32
	 * @Description: 通过likename、likepath、path、userid查询符合条件的hdfs数据
	 * @param @param params
	 * @param @return
	 * @return List<FileOrm>
	 * @throws
	 */
	List<FileOrm> query(Map<String, Object> params);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:49:21
	 * @Description: 通过likename、likepath、path、userid查询符合条件的hdfs数据条数
	 * @param @param params
	 * @param @return
	 * @return int
	 * @throws
	 */
	int count(Map<String, Object> params);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:50:50
	 * @Description: 批量添加hdfs数据给部门
	 * @param @param deptIDs
	 * @param @param fileIDs
	 * @return void
	 * @throws
	 */
	void saveDeptFiles(@Param("deptIds") List<Integer> deptIDs, 
					   @Param("fileIds") List<Integer> fileIDs);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月14日 下午2:51:10
	 * @Description: 批量添加hdfs数据给用户
	 * @param @param userIDs
	 * @param @param fileIDs
	 * @return void
	 * @throws
	 */
	void saveUserFiles(@Param("userIds") List<Integer> userIDs, 
					   @Param("fileIds") List<Integer> fileIDs);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月15日 上午11:02:18
	 * @Description: 批量删除部门对应的hdfs数据
	 * @param @param deptIDs
	 * @param @param fileIDs
	 * @return void
	 * @throws
	 */
	void removeDeptFiles(@Param("deptIds") List<Integer> deptIDs, 
						 @Param("fileIds") List<Integer> fileIDs);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月15日 上午11:03:23
	 * @Description: 批量删除用户对应的hdfs数据
	 * @param @param userIDs
	 * @param @param fileIDs
	 * @return void
	 * @throws
	 */
	void removeUserFiles(@Param("userIds") List<Integer> userIDs, 
						 @Param("fileIds") List<Integer> fileIDs);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年3月1日 上午10:14:10
	 * @Description:查询用户有权限的文件
	 * @param key
	 *            userid 用户ID 匹配格式 ： = deptidtree 用户部门的ID树结构 匹配格式 ： = likename
	 *            名称模糊查询 匹配格式： %xxx% likepath 路径模糊查询 匹配格式：xxx% path 路径全匹配 匹配格式：=
	 * @return List<FileOrm>
	 * @throws:
	 */
	List<FileOrm> queryForUser(Map<String, Object> params);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年3月1日 上午10:14:10
	 * @Description:查询用户有权限的文件记录数
	 * @return List<FileOrm>
	 * @throws:
	 */
	int countForUser(Map<String, Object> params);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月15日 上午11:04:34
	 * @Description: 查询所有的hdfs数据
	 * @param @return
	 * @return List<FileOrm>
	 * @throws
	 */
	List<FileOrm> queryAll();

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月15日 上午11:05:07
	 * @Description: 部门管理员更新除自身外的本部门下的所有用户的hdfs数据权限为不可见（auth=1）
	 * @param @param fid
	 * @param @param uid
	 * @param @param idtree
	 * @return void
	 * @throws
	 */
	void updateUFNot(@Param("fileId") int fid, @Param("userId") int uid, 
					 @Param("idtree") String idtree);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月15日 上午11:14:09
	 * @Description: 部门管理员更新包含自身在内的本部门下的所有用户的hdfs数据权限为不可见（auth=1）
	 * @param @param fid
	 * @param @param idtree
	 * @return void
	 * @throws
	 */
	void updateUFAllNot(@Param("fileId") int fid, @Param("idtree") String idtree);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月15日 上午11:22:16
	 * @Description: 更新用户对应的hdfs数据为可见（auth = 2）
	 * @param @param fid
	 * @param @param uid
	 * @return void
	 * @throws
	 */
	void updateUFByfid(@Param("fileId") int fid, @Param("userId") int uid);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月18日 上午9:38:35
	 * @Description: 更新用户对应的hdfs数据为不可见（auth = 1）
	 * @param @param fid
	 * @param @param userid
	 * @return void
	 * @throws
	 */
	void deleteUserByfid(@Param("fileId") int fid, @Param("userId") int userid);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月18日 上午9:42:53
	 * @Description: 更新部门对应的hdfs数据为不可见（auth = 1）
	 * @param @param fid
	 * @param @param did
	 * @return void
	 * @throws
	 */
	void deleteDeptByfid(@Param("fileId") int fid, @Param("deptId")int did);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月18日 上午10:41:15
	 * @Description: 通过fid、userid用户与hdfs数据的对应关系
	 * @param @param fid
	 * @param @param userid
	 * @param @return
	 * @return UserFiles
	 * @throws
	 */
	UserFiles queryByFidAndUid(@Param("fileId") int fid, @Param("userId")int userid);

	/**
	 * 
	 * @author lichao
	 * @date 2017年12月18日 上午10:41:23
	 * @Description: 通过fid、did查询部门与hdfs数据的对应关系
	 * @param @param fid
	 * @param @param did
	 * @param @return
	 * @return DeptFiles
	 * @throws
	 */
	DeptFiles queryByFidAndDid(@Param("fileId") int fid, @Param("deptId")int did);

}

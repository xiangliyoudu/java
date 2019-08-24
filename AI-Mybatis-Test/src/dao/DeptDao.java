package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.Dept;
import vo.ComboTreeVO;

public interface DeptDao {
	/**
	 * 
	 * @author lichao
	 * @date 2017年3月3日 下午2:49:19
	 * @Description: TODO 异步获取部门树 （未用）
	 * @param @param pid
	 * @param @return
	 * @return List<Dept>
	 * @throws
	 */
	List<Dept> depts(@Param("parentId") int pid);

	/**
	 * 
	 * @author lichao
	 * @date 2017年3月3日 下午2:50:14
	 * @Description: TODO 通过depId获取该部门下的用户集合
	 * @param @param depId
	 * @param @return
	 * @return List<Dept>
	 * @throws
	 */
	List<Dept> deptsByDepNO(@Param("deptId") int depId);

	/**
	 * 
	 * @author lichao
	 * @date 2017年3月3日 下午2:52:30
	 * @Description: TODO一次性获取部门树
	 * @param @param pid
	 * @param @return
	 * @return List<ComboTreeVO>
	 * @throws
	 */
	// 未实现，已通过tree(int pid, String idtree2)实现，传入第二个参数为null
	List<ComboTreeVO> tree(@Param("parentId") int pid);
	
	List<ComboTreeVO> tree(@Param("parentId") int pid, @Param("idtree2") String idtree2);

	/**
	 * 
	 * @author lichao
	 * @date 2017年3月3日 下午2:52:54
	 * @Description: TODO通过id查询部门信息
	 * @param @param id
	 * @param @return
	 * @return Dept
	 * @throws
	 */
	Dept byId(@Param("id") int id);

	/**
	 * 
	 * @author lichao
	 * @date 2017年3月3日 下午3:51:49
	 * @Description: TODO通过id逻辑删除
	 * @param @param id
	 * @return void
	 * @throws
	 */
	void delete(@Param("id") int id);

	void update(int id, String idtree, int state);

	/**
	 * 
	 * @author lichao
	 * @date 2017年3月3日 下午3:52:14
	 * @Description: TODO查询（state=1） 所有部门
	 * @param @param params
	 * @param @return
	 * @return List<Dept>
	 * @throws
	 */
	List<Dept> query(Map<String, Object> params);
	
	int count(Map<String, Object> params);

	/**
	 * 
	 * @author lichao
	 * @date 2017年3月3日 下午3:51:09
	 * @Description: TODO 编辑部门
	 * @param @param dept
	 * @return void
	 * @throws
	 */
	void update(Dept dept);

	/**
	 * 
	 * @author lichao
	 * @date 2017年3月3日 下午4:00:29
	 * @Description: TODO 通过uid获取用户所在部门
	 * @param @param uid
	 * @param @return
	 * @return Dept
	 * @throws
	 */
	Dept queryByUserid(@Param("userId") int uid);

	/**
	 * 
	 * @author lichao
	 * @date 2017年3月3日 下午3:58:23
	 * @Description: TODO 保存部门信息
	 * @param @param dept
	 * @return void
	 * @throws
	 */
	void save(Dept dept);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年3月7日 上午10:36:09
	 * @Description:查询所有部门信息进行缓存
	 * @return List<Dept>
	 * @throws:
	 */
	List<Dept> queryAll();

	void updateIdtree(@Param("idtree1") String idtree1, 
					  @Param("idtree2") String idtree2, 
					  @Param("idtree3") String idtree3);
}

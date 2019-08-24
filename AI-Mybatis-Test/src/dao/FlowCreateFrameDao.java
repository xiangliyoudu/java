package dao;

import java.util.List;
import java.util.Map;

import pojo.FlowCreateFrame;

public interface FlowCreateFrameDao {
	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月18日 下午5:19:41
	 * @Description:保存创建数据集的节点信息
	 * @param bo
	 *            void
	 * @throws:
	 */
	void save(FlowCreateFrame bo);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月18日 下午5:20:22
	 * @Description:修改流程节点
	 * @param bo
	 *            void
	 * @throws:
	 */
	void update(FlowCreateFrame bo);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月19日 上午9:33:24
	 * @Description:根据id进行删除
	 * @param id
	 *            void
	 * @throws:
	 */
	void delete(int id);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月19日 上午9:33:24
	 * @Description:根据节点id进行删除
	 * @param id
	 *            void
	 * @throws:
	 */
	void deleteByItemId(int id);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月18日 下午5:24:56
	 * @Description:查询创建数据集节点中的创建数据集信息
	 * @param params
	 *            keys itemid 节点id
	 * 
	 * @return List<FlowCreateFrame>
	 * @throws:
	 */
	List<FlowCreateFrame> query(Map<String, Object> params);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月18日 下午5:32:21
	 * @Description:根据id查询创建数据集信息
	 * @param id
	 * @return FlowCreateFrame
	 * @throws:
	 */
	FlowCreateFrame queryById(int id);
}

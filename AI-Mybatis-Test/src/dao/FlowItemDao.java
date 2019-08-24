package dao;

import java.util.List;
import java.util.Map;

import pojo.FlowItem;

public interface FlowItemDao {
	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月18日 下午5:19:41
	 * @Description:保存流程节点
	 * @param bo
	 *            void
	 * @throws:
	 */
	void save(FlowItem bo);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月18日 下午5:20:22
	 * @Description:修改流程节点
	 * @param bo
	 *            void
	 * @throws:
	 */
	void update(FlowItem bo);

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
	 * @data:2017年4月18日 下午5:24:56
	 * @Description:查询节点信息
	 * @param params
	 *            keys bookid 工程id type 节点类型
	 * 
	 * @return List<FlowItem>
	 * @throws:
	 */
	List<FlowItem> query(Map<String, Object> params);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月18日 下午5:30:02
	 * @Description:根据id查询节点信息
	 * @param id
	 * @return FlowItem
	 * @throws:
	 */
	FlowItem queryById(int id);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月19日 上午10:26:24
	 * @Description:查询下级节点
	 * @param itemid
	 * @return List<FlowItem>
	 * @throws:
	 */
	List<FlowItem> queryNextItems(int itemid);

	List<FlowItem> queryNextItems(int itemid, String type);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月19日 上午10:26:24
	 * @Description:查询上级节点
	 * @param itemid
	 * @return List<FlowItem>
	 * @throws:
	 */
	List<FlowItem> queryPrevItems(int itemid);

	List<FlowItem> queryPrevItems(int itemid, String type);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月19日 上午10:28:21
	 * @Description:节点关联
	 * @param sourceid
	 * @param targetid
	 *            void
	 * @throws:
	 */
	void connItems(int sourceid, int targetid);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月19日 上午10:28:21
	 * @Description:取消两个节点间的联系
	 * @param sourceid
	 * @param targetid
	 *            void
	 * @throws:
	 */
	void disConnBetweenItems(int sourceid, int targetid);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月19日 上午10:31:40
	 * @Description:取消节点所有的关系
	 * @param itemid
	 *            void
	 * @throws:
	 */
	void disConnAllItems(int itemid);

	/**
	 * 
	 * @author:lichao
	 * @data:2018年1月31日 上午10:31:40
	 * @Description:通过模型名称获取到item消息
	 * @param modelid
	 *            void
	 * @throws:
	 */
	FlowItem queryByModelId(String mid);
}

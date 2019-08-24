package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.FlowBuildModel;

public interface FlowBuildModelDao {
	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月18日 下午5:19:41
	 * @Description:保存建立模型的节点信息
	 * @param bo
	 *            void
	 * @throws:
	 */
	void save(FlowBuildModel bo);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月18日 下午5:20:22
	 * @Description:修改流程节点
	 * @param bo
	 *            void
	 * @throws:
	 */
	void update(FlowBuildModel bo);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月19日 上午9:33:24
	 * @Description:根据id进行删除
	 * @param id
	 *            void
	 * @throws:
	 */
	void delete(@Param("id") int id);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月19日 上午9:33:24
	 * @Description:根据节点id进行删除
	 * @param id
	 *            void
	 * @throws:
	 */
	void deleteByItemId(@Param("itemId") int id);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月18日 下午5:24:56
	 * @Description:查询建立模型节点中的模型参数信息
	 * @param params
	 *            keys itemid 节点id
	 * 
	 * @return List<FlowBuildModel>
	 * @throws:
	 */
	List<FlowBuildModel> query(Map<String, Object> params);

	/**
	 * 
	 * @author:zhengzk
	 * @data:2017年4月18日 下午5:32:21
	 * @Description:根据id查询建立模型信息
	 * @param id
	 * @return FlowCreateFrame
	 * @throws:
	 */
	FlowBuildModel queryById(@Param("id") int id);

	/**
	 * 
	 * @author:lichao
	 * @data:2017年4月28日 下午5:32:21
	 * @Description:根据itemid查询模型信息
	 * @param id
	 * @return FlowCreateFrame
	 * @throws:
	 */
	FlowBuildModel queryByItemId(@Param("itemId") int itemId);
}

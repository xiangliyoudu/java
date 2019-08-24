package dao;

import java.util.List;
import java.util.Map;

import pojo.FlowMissValue;

/**
 * Created by yejianfeng on 2017/7/3.
 */
public interface FlowMissDataDao {

	/**
	 * 保存缺失值list
	 * 
	 * @param datalist
	 */
	void saveList(List<FlowMissValue> datalist);

	/**
	 * 增加缺失值
	 * 
	 * @param missValue
	 */

	void save(FlowMissValue missValue);

	/**
	 * 更新插入缺失值的记录
	 * 
	 * @param bo
	 */

	void update(FlowMissValue bo);

	/**
	 * 根据id 删除缺失值的记录
	 * 
	 * @param id
	 */

	void delete(int id);

	/**
	 * 根据itemid删除记录
	 * 
	 * @param itemID
	 */
	void deleteByItemId(int itemID);

	/**
	 * 根据条件查询插入缺失值的记录list
	 * 
	 * @param params
	 * @return
	 */

	List<FlowMissValue> query(Map<String, Object> params);

	/**
	 * 根据id 查询缺失值
	 * 
	 * @param id
	 * @return
	 */

	FlowMissValue queryById(int id);

	/**
	 * 根据itemid查询插入缺失值的记录
	 *
	 * @param itemId
	 * @return
	 */

	FlowMissValue queryByItemId(int itemId);
}

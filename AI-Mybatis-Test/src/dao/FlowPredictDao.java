package dao;

import pojo.FlowPredict;

/**
 * Created by yejianfeng on 2017/4/28 15:51.
 */

public interface FlowPredictDao {
	/**
	 * 添加一个预测数据
	 * 
	 * @param flowPredict
	 */
	public void add(FlowPredict flowPredict);

	/**
	 * 删除一个预测数据
	 * 
	 * @param flowPredict
	 */
	public void del(FlowPredict flowPredict);

	/**
	 * g更新一个预测数据
	 * 
	 * @param flowPredict
	 */
	public void update(FlowPredict flowPredict);

	/**
	 * 通过id查询一个预测数据
	 * 
	 * @param id
	 * @return
	 */
	public FlowPredict queryById(int id);

	void deleteByitemId(int itemid);

	/**
	 * 通过预测的预测数据集名删除
	 * 
	 * @param predictName
	 */
	public void delByName(String predictName);

	/**
	 * 通过预测的数据集名查询，因为预测数据集后台随机生成，可以认为唯一
	 * 
	 * @param predictName
	 * @return
	 */
	public FlowPredict queryByName(String predictName);

	public void delById(int id);

	public FlowPredict qeueryByItemId(int id);

}

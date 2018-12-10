/*
 * Powered By [rapid-framework]
 * Web Site: http://blog.csdn.net/houfeng30920/article/details/52730893
 * Csdn Code: 
 * Since 2015 - 2017
 */

package cn.dao;

import java.util.List;
import java.util.Map;

import cn.pojo.AdPromotion;

/**
 *
 * @author hou
 * @version 1.0
 * @since 1.0
 * */
public interface IAdPromotionDao {

	/**
	 * 创建AdPromotion
	 **/
	public void save(AdPromotion adPromotion);

	/**
	 * 更新AdPromotion
	 **/
	public void update(AdPromotion adPromotion);

	/**
	 * 删除AdPromotion
	 **/
	public void deleteById(java.lang.Long id);

	/**
	 * 根据ID查询AdPromotion
	 **/
	public AdPromotion findById(java.lang.Long id);

	/**
	 * 查询: AdPromotion
	 **/
	public List<AdPromotion> findAll(Map<String, Object> paraMap);

}

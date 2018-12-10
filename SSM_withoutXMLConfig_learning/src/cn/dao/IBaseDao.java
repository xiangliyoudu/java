/*
 * Powered By [rapid-framework]
 * Web Site: http://blog.csdn.net/houfeng30920/article/details/52730893
 * Csdn Code: 
 * Since 2015 - 2017
 */

package cn.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hou
 * @version 1.0
 * @since 1.0
 * */
public interface IBaseDao<T>{
	
	//带条件查询，条件可以为null，既没有条件；返回list对象集合
	public List<T> find(Map<String, Object> paraMap);			
	
	//查询 ID为参数
	public T findById(Serializable id);
	
	//新建 用实体作为参数
	public void save(T entity);					
	
	//更新 用实体作为参数
	public void update(T entity);					
	
	//按id删除，删除一条；支持整数型和字符串类型ID
	public void deleteById(Serializable id);	
    
}

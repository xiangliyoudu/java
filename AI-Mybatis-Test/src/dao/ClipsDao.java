package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Clips;

public interface ClipsDao {
	/*
	 * 新增面板夹存储
	 */
	public void insert(Clips clips);

	/**
	 * 修改
	 * 
	 * @param clips
	 */
	public void update(Clips clips);

	/**
	 * 删除一个面板夹存储
	 * 
	 * @param id
	 */
	public void delete(@Param("id") int id);

	/**
	 * 查询用户所有的面板夹存储
	 * 
	 * @param userid
	 * @return
	 */
	public List<Clips> queryByUid(@Param("userId") int userid);
}

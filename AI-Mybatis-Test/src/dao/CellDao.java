package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Cell;

/**
 * @author
 * @desc BDTM平台前端代码编辑框对象持久接口
 */
public interface CellDao {
	/*
	 * 新增工程下的所有编辑框
	 */
	public void insertList(@Param("cells") List<Cell> cells);

	/**
	 * 删除工程下的编辑框
	 * 
	 * @param cells
	 */
	public void deleteList(@Param("id") int nid);

	/**
	 * 查询工程下的编辑框
	 * 
	 * @param nid
	 * @return
	 */
	public List<Cell> queryByNid(@Param("notebookId") int nid);

}

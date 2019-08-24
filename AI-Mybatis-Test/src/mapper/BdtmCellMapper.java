package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.BdtmCell;
import pojo.BdtmCellWithBLOBs;

public interface BdtmCellMapper {
	/**
	 * insert 
	 * @param cells
	 */
	public void insertList(List<BdtmCell> cells);
	
	/**
	 * delete by notebookid
	 * @param nid
	 */
	public void deleteList(@Param("notebookId") int notebookid);
	
	/**
	 * list by notebookid
	 * @param nid
	 * @return
	 */
	public List<BdtmCellWithBLOBs> queryByNid(@Param("notebookId") int nootbookid);
}
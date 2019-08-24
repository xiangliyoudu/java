package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import pojo.Cell;
import util.SqlSessionUtil;
import dao.CellDao;

public class TestCell {
	private Logger logger = Logger.getLogger(TestCell.class);

	SqlSession sqlSession = null;

	@Test
	public void testInsertList() {
		sqlSession = SqlSessionUtil.getSqlSession();
		CellDao dao = sqlSession.getMapper(CellDao.class);
		
		List<Cell> cells = new ArrayList<Cell>();
		Cell c1 = new Cell();
		c1.setNotebookId(1500);
		c1.setType("cs");
		c1.setInput("1111111111111");
		c1.setOutput("aaaaaaaaaaaa");
		
		Cell c11 = new Cell();
		c11.setNotebookId(1500);
		c11.setType("cs");
		c11.setInput("2222222222");
		c11.setOutput("bbbbbbbbbb");
		
		cells.add(c1);
		cells.add(c11);
		
		dao.insertList(cells);
		sqlSession.commit();
		sqlSession.close();
	}
	
	
	@Test
	public void testDeleteList() {
		sqlSession = SqlSessionUtil.getSqlSession();
		CellDao dao = sqlSession.getMapper(CellDao.class);
		
		dao.deleteList(1500);
		
		sqlSession.commit();
	}
	
	@Test
	public void testQueryByNid() {
		sqlSession = SqlSessionUtil.getSqlSession();

		CellDao dao = sqlSession.getMapper(CellDao.class);
		List<Cell> cells = dao.queryByNid(928);
		logger.info(cells.size());
	}

	

}








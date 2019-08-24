package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import pojo.Dept;
import util.SqlSessionUtil;
import vo.ComboTreeVO;
import dao.DeptDao;

public class TestDept {
	private Logger logger = Logger.getLogger(TestDept.class);
	static SqlSession sqlSession = null;
	static DeptDao dao = null;
	
	static{
		sqlSession = SqlSessionUtil.getSqlSession();
		dao = sqlSession.getMapper(DeptDao.class);
	}
	
	@Test
	public void testdepts() {
		List<Dept> ds = dao.depts(1);
		logger.info(ds.size());
	}
	
	@Test
	public void testdeptsByDepNO() {
		List<Dept> ds = dao.deptsByDepNO(1);
		logger.info(ds.size());
	}
	
	@Test
	public void testtree() {
		List<ComboTreeVO> ds = dao.tree(1);
		logger.info(ds.size());
	}
	
	/*@Test
	public void testtree2() {
		List<ComboTreeVO> ds = dao.tree(1, "0_1");
		logger.info(ds.size());
	}*/
	
	@Test
	public void testbyId() {
		Dept d = dao.byId(1);
		logger.info(d);
	}
	
	@Test
	public void testdelete() {
		dao.delete(82);
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	public void testupdate() {
		Dept d = new Dept();
//		d.setState(1);
//		d.setIdtree("0_1_81");
		d.setId(81);
		d.setDescription("update test");
		dao.update(d);
//		sqlSession.commit();
//		sqlSession.close();
	}
	
	@Test
	public void testquery() {
		Map<String, Object> paras = new HashMap<String, Object>();
//		paras.put("likename", "d");
		paras.put("offset", 5);
		paras.put("rows", 1);
		List<Dept> ds = dao.query(paras);
		logger.info(ds.size());
	}
	
	@Test
	public void testcount() {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("name", "d");
		int ds = dao.count(paras);
		logger.info(ds);
	}
	
	@Test
	public void testqueryByUserid() {
		Dept ds = dao.queryByUserid(1);
		logger.info(ds.getName());
	}

	@Test
	public void testsave() {
		Dept d = new Dept();
		d.setName("testest");
		d.setPid(1);
		d.setIdtree("0_1_");
		d.setState(1);

		dao.save(d);
		//sqlSession.commit();
		sqlSession.close();
		
	}

	@Test
	public void testqueryAll() {
		List<Dept> ds = dao.queryAll();
		logger.info(ds.size());
	}
	
	@Test
	public void testupdateIdtree() {
		String idtree1 = "86";
		String idtree2 = "866";
		String idtree3 = "0_1_86";
		dao.updateIdtree(idtree1, idtree2, idtree3);
		
		sqlSession.commit();
		sqlSession.close();
	}
	
	
}








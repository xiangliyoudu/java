package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import pojo.Dept;
import pojo.FileOrm;
import pojo.User;
import util.SqlSessionUtil;
import dao.FileOrmDao;

public class TestFileOrm {
	private Logger logger = Logger.getLogger(TestFileOrm.class);
	static SqlSession sqlSession = null;
	static FileOrmDao dao = null;
	
	static{
		sqlSession = SqlSessionUtil.getSqlSession();
		dao = sqlSession.getMapper(FileOrmDao.class);
	}
	
	@Test
	public void testhdfsHaveUserAndNot() {
		List<User> users = dao.hdfsHaveUserAndNot(621, "0_1_", 1);
		logger.info(users.size());
	}
	
	@Test
	public void testhdfsHaveDeptAndNot() {
		List<Dept> users = dao.hdfsHaveDeptAndNot(621);
		logger.info(users.size());
	}
	
	@Test
	public void testdelete() {
		dao.delete(1000);
	}
	
	@Test
	public void testdeleteByName() {
		dao.deleteByName("aaa");
	}
	
	@Test
	public void testsave() {
		FileOrm fo = new FileOrm();
		fo.setName("testest.csv");
		fo.setIspublic(1);
		fo.setUserid(1);
		fo.setPath("jalsjdflwjlf");
		fo.setFsize(123);

		dao.save(fo);
//		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	public void testqueryByID() {
		FileOrm fo = dao.queryByID(621);
		logger.info(fo.getName());
	}
	
	@Test
	public void testqueryByName() {
		FileOrm fo = dao.queryByName("model1_file.csv");
		logger.info(fo.getId());
	}
	
	@Test
	public void testdeleteUserByUid() {
		dao.deleteUserByUid(10000);
		sqlSession.close();
	}
	
	@Test
	public void testupdate() {
		FileOrm fo = new FileOrm();
		fo.setName("testest.csv");
		fo.setDescinfo("testest");
		fo.setId(621);
		
		dao.update(fo);
		sqlSession.close();
	}
	
	@Test
	public void testsaveUserFile() {
		dao.saveUserFile(1, 1000);
		
		sqlSession.close();
	}
	
	@Test
	public void testquery() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("likename", "b");
//		params.put("offset", 5);
//		params.put("rows", 2);
		
		List<FileOrm> fos = dao.query(params);
		logger.info(fos.get(1).getName());
		sqlSession.close();
	}
	
	@Test
	public void testcount() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("likename", "b");
		int i = dao.count(params);
		logger.info(i);
		
		sqlSession.close();
	}
	
	@Test
	public void testsaveDeptFiles() {
		List<Integer> deptIds = new ArrayList<Integer>();
		deptIds.add(11111);
		deptIds.add(22222);
		List<Integer> fileIds = new ArrayList<Integer>();
		fileIds.add(33333);
		fileIds.add(44444);
		
		dao.saveDeptFiles(deptIds, fileIds);
		sqlSession.close();
	}
	
	@Test
	public void testsaveUserFiles() {
		List<Integer> userIds = new ArrayList<Integer>();
		userIds.add(11111);
		userIds.add(22222);
		List<Integer> fileIds = new ArrayList<Integer>();
		fileIds.add(33333);
		fileIds.add(44444);
		
		dao.saveUserFiles(userIds, fileIds);
		sqlSession.close();
	}
	
	@Test
	public void testremoveDeptFiles() {
		List<Integer> deptIds = new ArrayList<Integer>();
		deptIds.add(11111);
		deptIds.add(22222);
		List<Integer> fileIds = new ArrayList<Integer>();
		fileIds.add(33333);
		fileIds.add(44444);
		
		dao.removeDeptFiles(deptIds, fileIds);
		sqlSession.close();
	}
	
	@Test
	public void testremoveUserFiles() {
		List<Integer> userIds = new ArrayList<Integer>();
		userIds.add(11111);
		userIds.add(22222);
		List<Integer> fileIds = new ArrayList<Integer>();
		fileIds.add(33333);
		fileIds.add(44444);
		
		dao.removeUserFiles(userIds, fileIds);
		sqlSession.close();
	}
	
	@Test
	public void testqueryForUser() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("likename", "b");
		params.put("offset", 5);
		params.put("rows", 1);
		
		List<FileOrm> fos = dao.queryForUser(params );
		logger.info(fos.size());
		sqlSession.close();
	}
	
	
	
	
}








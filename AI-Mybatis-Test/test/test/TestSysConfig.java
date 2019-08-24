package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import pojo.SysConfig;
import util.SqlSessionUtil;
import dao.ConfigDao;

public class TestSysConfig {
	private Logger logger = Logger.getLogger(TestSysConfig.class);
	static SqlSession sqlSession = null;
	static ConfigDao dao = null;
	
	static{
		sqlSession = SqlSessionUtil.getSqlSession();
		dao = sqlSession.getMapper(ConfigDao.class);
	}
	
	@Test
	public void testgetByKey() {
		SysConfig c = dao.getByKey("00:21:CC:D9:46:F9");
		logger.info(c.getValue());
	}
	
	@Test
	public void testgetAll() {
		List<SysConfig> c = dao.getAll();
		logger.info(c.size());
	}
	
	@Test
	public void testupdate() {
		SysConfig sc = new SysConfig();
		sc.setKey("123456");
		sc.setValue("000000");
		dao.update(sc);
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	public void testinsert() {
		SysConfig sc = new SysConfig();
		sc.setKey("123456");
		sc.setValue("abcdefg");
		sc.setBz("just for test");
		dao.insert(sc);
		sqlSession.commit();
		sqlSession.close();
	}

	

}








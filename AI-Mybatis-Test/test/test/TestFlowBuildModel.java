package test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import pojo.FlowBuildModel;
import util.SqlSessionUtil;
import dao.FlowBuildModelDao;

public class TestFlowBuildModel {
	private Logger logger = Logger.getLogger(TestFlowBuildModel.class);

	static SqlSession sqlSession = null;
	static FlowBuildModelDao dao = null;
	
	static{
		sqlSession = SqlSessionUtil.getSqlSession();
		dao = sqlSession.getMapper(FlowBuildModelDao.class);
	}

	@Test
	public void testsave() {
		FlowBuildModel fbm = new FlowBuildModel();
		fbm.setAlgorithm("test");
		fbm.setParamters("aaa");
		fbm.setModelid("1000");
		fbm.setItemid(12);

		dao.save(fbm);
		
		sqlSession.close();
	}
	
	@Test
	public void testupdate() {
		FlowBuildModel fbm = new FlowBuildModel();
		fbm.setAlgorithm("test");
		fbm.setParamters("aaa");
		fbm.setModelid("1000");
		fbm.setItemid(12);
		fbm.setId(1000);
		
		dao.save(fbm);
		
		sqlSession.close();
	}
	
	@Test
	public void testdelete() {
		dao.delete(1000);
		
		sqlSession.close();
	}

	@Test
	public void testquery() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", 5);
		params.put("rows", 1);
		params.put("itemid", 3821);
		
		List<FlowBuildModel> fbms = dao.query(params );
		logger.info(fbms.size());
		sqlSession.close();
	}
	
	@Test
	public void testqueryById() {
		
		FlowBuildModel fbm = dao.queryById(400);
		logger.info(fbm.getAlgorithm());
		sqlSession.close();
	}

	@Test
	public void testqueryByItemId() {
		
		FlowBuildModel fbm = dao.queryByItemId(3818);
		logger.info(fbm.getAlgorithm());
		sqlSession.close();
	}
}








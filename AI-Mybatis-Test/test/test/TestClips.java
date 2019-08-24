package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import pojo.Clips;
import util.SqlSessionUtil;
import dao.ClipsDao;

public class TestClips {
	private Logger logger = Logger.getLogger(TestClips.class);

	SqlSession sqlSession = null;

	@Test
	public void testInsert() {
		sqlSession = SqlSessionUtil.getSqlSession();
		ClipsDao dao = sqlSession.getMapper(ClipsDao.class);
		
		Clips clips = new Clips();
		clips.setType("cs");
		clips.setInput("ddddddd");
		clips.setUserid(1);
		
		dao.insert(clips);
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	public void testDelete() {
		sqlSession = SqlSessionUtil.getSqlSession();
		ClipsDao dao = sqlSession.getMapper(ClipsDao.class);
		
		dao.delete(130);
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	public void testQueryByUid() {
		sqlSession = SqlSessionUtil.getSqlSession();
		ClipsDao dao = sqlSession.getMapper(ClipsDao.class);
		
		List<Clips> cs = dao.queryByUid(1);
		logger.info(cs.size());
	}
	
	@Test
	public void testUpdate() {
		sqlSession = SqlSessionUtil.getSqlSession();
		ClipsDao dao = sqlSession.getMapper(ClipsDao.class);
		
		Clips clips = new Clips();
		clips.setId(130);
		clips.setType("md");
		clips.setInput("eeeeee");
		
		dao.update(clips);
		sqlSession.commit();
		sqlSession.close();
	}
	
}








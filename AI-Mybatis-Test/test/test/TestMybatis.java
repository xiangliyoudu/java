package test;

import java.util.List;

import mapper.BdtmCellMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import pojo.BdtmCellWithBLOBs;
import util.SqlSessionUtil;

public class TestMybatis {
	private Logger logger = Logger.getLogger(TestMybatis.class);

	SqlSession sqlSession = null;

	@Test
	public void test2() {
		sqlSession = SqlSessionUtil.getSqlSession();

		BdtmCellMapper dao = sqlSession.getMapper(BdtmCellMapper.class);
		List<BdtmCellWithBLOBs> cells = dao.queryByNid(928);
		logger.info(cells.get(5));
		
	}

}

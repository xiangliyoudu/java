package com.xlyd.mybatis.pagehelper;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xlyd.mybatis.pagehelper.dao.BookDao;
import com.xlyd.mybatis.pagehelper.entity.Book;

public class TestPerson {
	private Logger logger = Logger.getLogger(TestPerson.class);

	SqlSession sqlSession = null;

	@Test
	public void test2() {
		sqlSession = SqlSessionUtil.getSqlSession();
		BookDao dao = sqlSession.getMapper(BookDao.class);
		int size = dao.queryAll().size();
		logger.info(size);
		
		sqlSession.close();
	}
	
	@Test
	public void test3() {
		sqlSession = SqlSessionUtil.getSqlSession();
		BookDao dao = sqlSession.getMapper(BookDao.class);
		PageHelper.startPage(1, 5);
		List<Book> bs = dao.queryAll();
		PageInfo<Book> pi = new PageInfo<Book>(bs);
		
		bs = pi.getList();
		int pageNum = pi.getPageNum();
		long totalNum = pi.getTotal();
		
		System.out.println(pageNum);
		System.out.println(totalNum);
		
		sqlSession.close();
	}
	
	
	@Test
	public void test4() {
		sqlSession = SqlSessionUtil.getSqlSession();
		BookDao dao = sqlSession.getMapper(BookDao.class);
		PageHelper.startPage(1, 5);
		List<Book> bs = dao.queryByName("一");
		PageInfo<Book> pi = new PageInfo<Book>(bs);
		System.out.println(pi);
	}
	


	
	

}








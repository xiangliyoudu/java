package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import pojo.Book;
import util.SqlSessionUtil;
import dao.BookDao;

public class TestBook {
	private Logger logger = Logger.getLogger(TestBook.class);
	
	SqlSession sqlSession = null;
	
	@Test
	public void testInsertList() {
		sqlSession = SqlSessionUtil.getSqlSession();
		BookDao dao = sqlSession.getMapper(BookDao.class);
		
		List<String> bookNames = new ArrayList<String>();
		bookNames.add("aaaaaaa");
		bookNames.add("bbbbbbb");
		bookNames.add("ccccccc");
		bookNames.add("ddddddd");
		dao.insertList(bookNames);
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	public void testQueryAll(){
		sqlSession = SqlSessionUtil.getSqlSession();
		BookDao dao = sqlSession.getMapper(BookDao.class);
		List<Book> books = dao.queryAll();
		logger.info(books.size());
		sqlSession.close();
	}
}










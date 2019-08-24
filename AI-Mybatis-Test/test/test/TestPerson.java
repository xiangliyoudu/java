package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mapper.PersonDao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import pojo.Person;
import pojo.User;
import util.SqlSessionUtil;

public class TestPerson {
	private Logger logger = Logger.getLogger(TestPerson.class);

	SqlSession sqlSession = null;

	@Test
	public void test2() {
		sqlSession = SqlSessionUtil.getSqlSession();

		PersonDao dao = sqlSession.getMapper(PersonDao.class);
		List<User> users = dao.queryAll();
		logger.info(users.size());
	}

	@Test
	public void test3() {
		sqlSession = SqlSessionUtil.getSqlSession();

		PersonDao dao = sqlSession.getMapper(PersonDao.class);
		User users = dao.queryById(3);
		logger.info(users);

	}

	@Test
	public void testbatchSelect() throws IOException {
		sqlSession = SqlSessionUtil.getSqlSession();

		PersonDao dao = sqlSession.getMapper(PersonDao.class);
		List<Integer> ids =Arrays.asList(1, 2, 3, 4);
		List<User> emps = dao.getForeach(ids);
		for (User e : emps) {
			System.out.println(e);
		}

	}
	
	@Test
	public void testbatchAdd() throws IOException {
		sqlSession = SqlSessionUtil.getSqlSession();

		PersonDao dao = sqlSession.getMapper(PersonDao.class);
		List<Person> users = new ArrayList<Person>();
		
		Person u1 = new Person("python111", "py");
		Person u2 = new Person("java111", "jv");
		
		users.add(u1);
		users.add(u2);
		int num = dao.addForeach(users);
		System.out.println(num + "***");
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	public void testbatchDelete() throws IOException {
		sqlSession = SqlSessionUtil.getSqlSession();

		PersonDao dao = sqlSession.getMapper(PersonDao.class);
		List<Integer> ids = new ArrayList<Integer>();
		
		ids.add(21);
		ids.add(22);
		int num = dao.deleteForeach(ids);
		System.out.println(num + "***");
		sqlSession.commit();
		sqlSession.close();
	}

}








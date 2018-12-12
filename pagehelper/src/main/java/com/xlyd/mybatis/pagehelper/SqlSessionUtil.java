package com.xlyd.mybatis.pagehelper;


import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtil {
	private static SqlSession sqlSession = null;
	
	public static SqlSession getSqlSession() {

		try {
			Reader reader = Resources.getResourceAsReader("Mybatis-config.xml");
			// create factory
			SqlSessionFactory factory = new SqlSessionFactoryBuilder()
					.build(reader);
			// create SqlSession
			sqlSession = factory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sqlSession;
	}
	
	public static void closeSqlSession(){
		if(sqlSession != null){
			sqlSession.close();
		}
	}
}

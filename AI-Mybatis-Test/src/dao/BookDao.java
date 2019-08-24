package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Book;

public interface BookDao {
	void insertList(@Param("bookNames") List<String> bookNames);
	
	List<Book> queryAll();
}

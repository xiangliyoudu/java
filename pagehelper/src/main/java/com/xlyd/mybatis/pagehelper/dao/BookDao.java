package com.xlyd.mybatis.pagehelper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xlyd.mybatis.pagehelper.entity.Book;


public interface BookDao {
	
	void insertList(@Param("bookNames") List<String> bookNames);
	
	List<Book> queryAll();
	
	List<Book> queryByName(@Param("bookName") String bookName);
}

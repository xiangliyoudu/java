package com.xlyd.mybatis.pagehelper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xlyd.mybatis.pagehelper.entity.Photo;


public interface PhotoDao {
	
	List<Photo> queryAll();
	
	int insert(Photo photo);
	
	Photo selectById(@Param("id") int id);
	
}

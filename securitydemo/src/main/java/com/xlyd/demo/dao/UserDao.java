package com.xlyd.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xlyd.demo.entity.User;

@Repository
public interface UserDao {
	/**
	 * get all users
	 * @return
	 */
	List<User> findAll();
	
	User findById(@Param("id") int id);

	User findByUsername(@Param("userCode") String userCode);
	
}

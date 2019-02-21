package com.xlyd.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xlyd.demo.entity.Address;

@Repository
public interface AddressDao {

	List<Address> findByUserId(@Param("userId") Integer userId);
	
	Address findById(@Param("id") Integer id);
}

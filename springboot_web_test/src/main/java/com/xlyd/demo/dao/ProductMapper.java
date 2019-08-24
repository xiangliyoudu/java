package com.xlyd.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xlyd.demo.entity.Product;

@Mapper
public interface ProductMapper {

	@Select("select * from product")
	List<Product> findAll();
	
	@Update("")
	void update(Product p);

	@Delete("")
	void deleteById(int id);
	
	@Insert("insert into product values( #{name}, #{price}, #{instock} ")
	void insert(Product p);
	
}








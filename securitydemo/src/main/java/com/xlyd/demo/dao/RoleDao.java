package com.xlyd.demo.dao;

import com.xlyd.demo.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    Role findById(@Param("id") int id);

    List<Role> findAll();

}

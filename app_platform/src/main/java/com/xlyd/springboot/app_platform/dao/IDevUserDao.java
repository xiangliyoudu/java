/*
 * Powered By [rapid-framework]
 * Web Site: http://blog.csdn.net/houfeng30920/article/details/52730893
 * Csdn Code:
 * Since 2015 - 2017
 */

package com.xlyd.springboot.app_platform.dao;

import com.xlyd.springboot.app_platform.entity.DevUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IDevUserDao {

    /**
     * 创建DevUser
     **/
    void save(DevUser devUser);

    /**
     * 更新DevUser
     **/
    void update(DevUser devUser);

    /**
     * 删除DevUser
     **/
    void deleteById(Long id);

    /**
     * 根据ID查询DevUser
     **/
    DevUser findById(Long id);

    /**
     * 查询: DevUser
     **/
    List<DevUser> findAll(Map<String, Object> paraMap);

    DevUser findByNameAndPwd(@Param("userCode") String userCode,
                             @Param("userPassword") String userPassword);

    DevUser findByName(@Param("username") String username);
}

package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.SysConfig;

public interface ConfigDao {

	SysConfig getByKey(@Param("key") String key);

	List<SysConfig> getAll();

	void update(SysConfig bo);

	void insert(SysConfig bo);

}

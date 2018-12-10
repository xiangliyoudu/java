package com.xlyd.springboot.app_platform.service.backend;

import java.util.List;

import com.xlyd.springboot.app_platform.entity.AppCategory;

public interface IAppCategoryService {
	/**
	 * @return: 查询一级分类列表
	 */
	public List<AppCategory> findLevel1();
	
	/**
	 * @return: 查询二级、三级分类列表
	 */
	public List<AppCategory> findLevel23(Integer pid);
	
}

package com.xlyd.springboot.app_platform.service.backend;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xlyd.springboot.app_platform.dao.IAppCategoryDao;
import com.xlyd.springboot.app_platform.entity.AppCategory;
@Service("IAppCategoryServiceImpl")
public class IAppCategoryServiceImpl implements IAppCategoryService {

	@Resource
	private IAppCategoryDao iAppCategoryDao;
	@Override
	public List<AppCategory> findLevel1() {
		// TODO Auto-generated method stub
		return iAppCategoryDao.findLevel1();
	}

	@Override
	public List<AppCategory> findLevel23(Integer pid) {
		// TODO Auto-generated method stub
		return iAppCategoryDao.findLevel23(pid);
	}

	

}

package com.xlyd.springboot.app_platform.service.backend;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xlyd.springboot.app_platform.dao.IAppInfoDao;
import com.xlyd.springboot.app_platform.entity.AppInfo;

@Service("AppInfoServiceImpl")
public class AppInfoServiceImpl implements AppInfoService {

	@Resource
	private IAppInfoDao iAppInfoDao;

	@Override
	public List<AppInfo> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return iAppInfoDao.findAll(map);
	}

	@Override
	public Integer appCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return iAppInfoDao.appCount(map);
	}

	@Override
	public AppInfo findById(Integer aid) {
		// TODO Auto-generated method stub
		return iAppInfoDao.findById(aid);
	}

	@Override
	public int updateStatus(Integer modifyBy, Date modifyDate, Integer id,
			Integer status) {
		// TODO Auto-generated method stub
		return iAppInfoDao.updateStatus(modifyBy, modifyDate, id, status);
	}

	@Override
	public AppInfo findByAPKName(String apkName) {
		return iAppInfoDao.findByAPKName(apkName);
	}

	@Override
	public int addAppInfo(AppInfo appInfo) {
		int i = -1;
		try {
			i = iAppInfoDao.addAppInfo(appInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return i;
	}

	@Override
	public int updateVersionId(Integer modifyBy, Date modifyDate, Integer id,
			Integer versionId) {
		// TODO Auto-generated method stub
		return iAppInfoDao.updateVersionId(modifyBy, modifyDate, id, versionId);
	}

	@Override
	public Boolean emptyLogoPicPath(Integer modifyBy, Date modifyDate,
			Integer vid) {
		return iAppInfoDao.emptyLogoPicPath(modifyBy, modifyDate, vid);
	}

	@Override
	public int update(AppInfo appInfo) {
		// TODO Auto-generated method stub
		return iAppInfoDao.update(appInfo);
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return iAppInfoDao.deleteById(id);
	}

}

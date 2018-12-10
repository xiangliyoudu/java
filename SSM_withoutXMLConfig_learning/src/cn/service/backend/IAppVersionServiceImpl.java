package cn.service.backend;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dao.IAppVersionDao;
import cn.pojo.AppVersion;

@Service("IAppVersionServiceImpl")
public class IAppVersionServiceImpl implements IAppVersionService {

	@Resource
	private IAppVersionDao iAppVersionDao;

	@Override
	public AppVersion findById(Integer vid) {
		// TODO Auto-generated method stub
		return iAppVersionDao.findById(vid);
	}

	@Override
	public List<AppVersion> findByAppInfoId(Integer id) {
		// TODO Auto-generated method stub
		return iAppVersionDao.findByAppInfoId(id);
	}

	@Override
	public int save(AppVersion appVersion) {
		// TODO Auto-generated method stub
		return iAppVersionDao.save(appVersion);
	}

	@Override
	public int update(AppVersion appVersion) {
		// TODO Auto-generated method stub
		return iAppVersionDao.update(appVersion);
	}

	@Override
	public Boolean emptyAPKFileName(Integer modifyBy, Date modifyDate,
			Integer vid) {
		// TODO Auto-generated method stub
		return iAppVersionDao.emptyAPKFileName(modifyBy, modifyDate, vid);
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return iAppVersionDao.deleteById(id);
	}




}

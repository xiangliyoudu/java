package cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dao.IBackendUserDao;
import cn.pojo.BackendUser;

@Service("iBackendUserServiceImpl")
public class IBackendUserServiceImpl implements IBackendUserService {

	@Resource
	private IBackendUserDao iBackendUserDao;

	@Override
	public BackendUser findByNameAndPwd(String userCode, String userPassword) {
		// TODO Auto-generated method stub
		return iBackendUserDao.findByNameAndPwd(userCode, userPassword);
	}

	public IBackendUserDao getIBackendUserDao() {
		return iBackendUserDao;
	}

	public void setIBackendUserDao(IBackendUserDao iBackendUserDao) {
		this.iBackendUserDao = iBackendUserDao;
	}

}

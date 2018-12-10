package cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dao.IDevUserDao;
import cn.pojo.DevUser;

@Service("IDevUserServiceImpl")
public class IDevUserServiceImpl implements IDevUserService {

	@Resource 
	private IDevUserDao iDevUserDao;
	@Override
	public DevUser findByNameAndPwd(String userCode, String userPassword) {
		
		return iDevUserDao.findByNameAndPwd(userCode, userPassword);
	}

}

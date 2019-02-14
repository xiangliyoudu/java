package com.xlyd.springboot.app_platform.service;

import com.xlyd.springboot.app_platform.dao.IDevUserDao;
import com.xlyd.springboot.app_platform.entity.DevUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("IDevUserServiceImpl")
public class IDevUserServiceImpl implements IDevUserService {

    @Resource
    private IDevUserDao iDevUserDao;

    @Override
    public DevUser findByNameAndPwd(String userCode, String userPassword) {

        return iDevUserDao.findByNameAndPwd(userCode, userPassword);
    }

    @Override
    public DevUser findByName(String username) {
        return iDevUserDao.findByName(username);
    }

}

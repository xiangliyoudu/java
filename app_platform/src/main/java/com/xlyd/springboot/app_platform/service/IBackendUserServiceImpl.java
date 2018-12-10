package com.xlyd.springboot.app_platform.service;

import com.xlyd.springboot.app_platform.dao.IBackendUserDao;
import com.xlyd.springboot.app_platform.entity.BackendUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iBackendUserServiceImpl")
public class IBackendUserServiceImpl implements IBackendUserService {

    @Autowired
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

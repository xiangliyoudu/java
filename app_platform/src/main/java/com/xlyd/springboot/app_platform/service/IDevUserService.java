package com.xlyd.springboot.app_platform.service;

import com.xlyd.springboot.app_platform.entity.DevUser;

public interface IDevUserService {

    DevUser findByNameAndPwd(String userCode, String userPassword);

    DevUser findByName(String username);

}

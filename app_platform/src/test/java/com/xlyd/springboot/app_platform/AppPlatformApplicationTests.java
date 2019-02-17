package com.xlyd.springboot.app_platform;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xlyd.springboot.app_platform.entity.AppInfo;
import com.xlyd.springboot.app_platform.entity.BackendUser;
import com.xlyd.springboot.app_platform.service.IBackendUserService;
import com.xlyd.springboot.app_platform.service.backend.AppInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppPlatformApplicationTests {
    Logger log = LoggerFactory.getLogger(AppPlatformApplicationTests.class);

    @Test
    public void contextLoads() {
    }

    @Autowired
    IBackendUserService service;

    @Autowired
    JdbcOperations jdbcOperations;

    @Autowired
    AppInfoService appInfoService;

    @Test
    public void testMapper() {
//        String userCode = "admin";
//        String userPassword = "123";
//        String sql = "select * from backend_user where userCode = ? and userPassword = ?";
//        RowMapper<BackendUser> rowMapper = BeanPropertyRowMapper.newInstance(BackendUser.class);
//        BackendUser backendUser = jdbcOperations.queryForObject(sql, rowMapper, userCode, userPassword);
//        log.info(backendUser.getUserName());

        BackendUser backendUser = service.findByNameAndPwd("admin", "123");
        log.info(backendUser.getUserName());

    }


    @Test
    public void testPageHelper() {
        PageHelper.startPage(1, 5);
        List<AppInfo> appInfos = appInfoService.findAll(null);
        log.info(appInfos.size() + "");
        PageInfo<AppInfo> pageInfo = new PageInfo<>(appInfos);
        log.info(pageInfo.getPages() + "");

    }
}




package com.xlyd.springboot.app_platform.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Data
public class DevUser implements UserDetails {
    private Integer id;// 主键id
    private String devCode;// 开发者帐号(系统登录账号)
    private String devName;// 开发者名称
    private String devPassword;// 开发者密码
    private String devEmail;// 开发者邮箱
    private String devInfo; // 开发者简介
    private Integer createdBy;// 创建者
    private Date creationDate;// 创建时间
    private Integer modifyBy;// 更新者
    private Date modifyDate;// 更新时间

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("dev"));
    }

    @Override
    public String getPassword() {
        return this.getDevPassword();
    }

    @Override
    public String getUsername() {
        return this.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

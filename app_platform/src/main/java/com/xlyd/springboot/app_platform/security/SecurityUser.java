package com.xlyd.springboot.app_platform.security;

import com.xlyd.springboot.app_platform.entity.BackendUser;
import com.xlyd.springboot.app_platform.entity.DevUser;
import com.xlyd.springboot.app_platform.service.IBackendUserService;
import com.xlyd.springboot.app_platform.service.IDevUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class SecurityUser implements UserDetailsService {

    private IBackendUserService iBackendUserService;

    private IDevUserService iDevUserService;

    public SecurityUser(IBackendUserService iBackendUserService, IDevUserService iDevUserService) {
        this.iBackendUserService = iBackendUserService;
        this.iDevUserService = iDevUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BackendUser backendUser = iBackendUserService.findByName(username);
        DevUser devUser = iDevUserService.findByName(username);

        User user = null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = null;

        if (backendUser != null) {
            authority = new SimpleGrantedAuthority("manager");
            authorities.add(authority);
            user = new User(username, backendUser.getUserPassword(), authorities);
        } else if (devUser != null) {
            authority = new SimpleGrantedAuthority("dev");
            authorities.add(authority);
            user = new User(username, devUser.getDevPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("user not found !!!");
        }

        return user;

    }
}

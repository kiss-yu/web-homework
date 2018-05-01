package com.nix.cinema.service.impl;

import com.nix.cinema.Exception.WebException;
import com.nix.cinema.common.cache.UserCache;
import com.nix.cinema.dao.UserMapper;
import com.nix.cinema.model.UserModel;
import com.nix.cinema.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Kiss
 * @date 2018/05/01 20:13
 * 用户service
 */
@Service
public class UserService extends BaseService<UserModel> {
    public final static String ADMIN_USERNAME = "admin";

    @Autowired
    private UserMapper userMapper;

    public UserModel login(String username, String password, HttpServletRequest request) {
        UserModel user = UserCache.currentUser();
        if (user == null) {
            user = userMapper.login(username,password);
            request.getSession(true).setAttribute(UserCache.USER_SESSION_KEY,user);
        }
        return user;
    }

    public UserModel registered(UserModel user,HttpServletRequest request) throws Exception {
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        add(user);
        user = findByUsername(user.getUsername());
        if (user != null) {
            request.getSession(true).setAttribute(UserCache.USER_SESSION_KEY,user);
        }
        return user;
    }

    public UserModel findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void add(UserModel model) throws Exception {
        if (ADMIN_USERNAME.equals(model.getUsername())) {
            throw new WebException(401,"不能使用admin做完用户名");
        }
        super.add(model);
    }
}

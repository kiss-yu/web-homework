package com.nix.cinema.service.impl;

import com.nix.cinema.common.cache.UserCache;
import com.nix.cinema.dao.UserMapper;
import com.nix.cinema.model.UserModel;
import com.nix.cinema.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kiss
 * @date 2018/05/01 20:13
 */
@Service
public class UserService extends BaseService<UserModel> {
    @Autowired
    private UserMapper userMapper;

    public UserModel login(String username, String password, HttpServletRequest request) {
//        UserModel user = userMapper.login(username,password);;
//        if (user != null) {
//            UserCache.putUser(user);
//        }
        UserModel userModel = UserCache.currentUser();
        System.out.println(userModel); {
            if (userModel == null) {
                userModel = new UserModel();
                userModel.setUsername(username);
                userModel.setPassword(password);
                UserCache.putUser(userModel);
                request.getSession(true).setAttribute(UserCache.USER_SESSION_KEY,userModel);
            }
        }
        return userModel;
    }

}

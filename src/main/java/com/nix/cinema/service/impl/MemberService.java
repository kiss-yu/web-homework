package com.nix.cinema.service.impl;

import com.nix.cinema.Exception.WebException;
import com.nix.cinema.common.cache.UserCache;
import com.nix.cinema.dao.MemberMapper;
import com.nix.cinema.model.MemberModel;
import com.nix.cinema.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kiss
 * @date 2018/05/01 20:13
 * 用户service
 */
@Service
public class MemberService extends BaseService<MemberModel> {
    public final static String ADMIN_USERNAME = "admin";

    @Autowired
    private MemberMapper userMapper;

    public MemberModel login(String username, String password, HttpServletRequest request) {
        MemberModel user = UserCache.currentUser();
        if (user == null) {
            user = userMapper.login(username,password);
            request.getSession(true).setAttribute(UserCache.USER_SESSION_KEY,user);
        }
        return user;
    }

    public MemberModel registered(MemberModel user, HttpServletRequest request) throws Exception {
        add(user);
        user = findByUsername(user.getUsername());
        if (user != null) {
            request.getSession(true).setAttribute(UserCache.USER_SESSION_KEY,user);
        }
        return user;
    }

    public MemberModel findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public MemberModel add(MemberModel model) throws Exception {
        if (ADMIN_USERNAME.equals(model.getUsername())) {
            throw new WebException(401,"不能使用admin做完用户名");
        }
        return super.add(model);
    }
}

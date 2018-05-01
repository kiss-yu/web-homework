package com.nix.cinema.dao;

import com.nix.cinema.dao.base.BaseMapper;
import com.nix.cinema.model.UserModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 11723
 */
@Repository
public interface UserMapper extends BaseMapper<UserModel> {
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     * */
    UserModel login(@Param("username") String username, @Param("password") String password);
}
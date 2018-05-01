package com.nix.cinema.common.cache;

import com.nix.cinema.model.UserModel;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kiss
 * @date 2018/05/01 20:08
 */
public final class UserCache {
    public final static String USER_SESSION_KEY = "user_id";
    //本地线程临时存储
    private final static ThreadLocal<UserModel> local = new ThreadLocal<>();

    public static UserModel currentUser() {
        return local.get();
    }
    public static void putUser(UserModel user) {
        local.set(user);
    }
}

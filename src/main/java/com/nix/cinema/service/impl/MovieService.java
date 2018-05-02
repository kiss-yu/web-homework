package com.nix.cinema.service.impl;

import com.nix.cinema.common.cache.UserCache;
import com.nix.cinema.dao.MovieMapper;
import com.nix.cinema.model.MovieModel;
import com.nix.cinema.model.UserModel;
import com.nix.cinema.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kiss
 * @date 2018/05/01 22:53
 * 电影service
 */
@Service
public class MovieService extends BaseService<MovieModel> {
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public MovieModel add(MovieModel model) throws Exception {
        UserModel currentUser = UserCache.currentUser();
        if (currentUser.isSuperAdmin()) {
            return super.add(model);
        }
        model.setUser(UserCache.currentUser());
        return super.add(model);
    }

    /**
     * 更新数据库中某个对象
     *
     * @param model 需要更新的对象
     * @return
     * @throws Exception 修改失败抛出异常
     */
    @Override
    public MovieModel update(MovieModel model) throws Exception {
        UserModel currentUser = UserCache.currentUser();
        if (currentUser.isSuperAdmin()) {
            return super.update(model);
        }
        model.setUser(currentUser);
        return movieMapper.updateCurrentUser(model);
    }
}

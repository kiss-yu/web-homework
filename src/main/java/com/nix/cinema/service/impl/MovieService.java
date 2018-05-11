package com.nix.cinema.service.impl;

import com.nix.cinema.common.cache.UserCache;
import com.nix.cinema.dao.MovieMapper;
import com.nix.cinema.model.CinemaModel;
import com.nix.cinema.model.MovieModel;
import com.nix.cinema.model.MemberModel;
import com.nix.cinema.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Kiss
 * @date 2018/05/01 22:53
 * 电影service
 */
@Service
public class MovieService extends BaseService<MovieModel> {
    private final static String MOVIE_IMG_PATH = "/images/movie/";
    private final static String MOVIE_DEFAULT_IMG = "default.jpg";
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public MovieModel add(MovieModel model) throws Exception {
        model.setMovieSn(model.getMovieSn() == null || model.getMovieSn().isEmpty() ? model.generateSn() : model.getMovieSn());
        return super.add(model);
    }

    public MovieModel add(MovieModel model, MultipartFile log) throws Exception {
        if (log != null && !log.isEmpty()) {
            File img = new File(this.getClass().getResource("/").getFile() + MOVIE_IMG_PATH + log.getOriginalFilename());
            log.transferTo(img);
            model.setImg(MOVIE_IMG_PATH + log.getOriginalFilename());
        } else {
            model.setImg(MOVIE_IMG_PATH + MOVIE_DEFAULT_IMG);
        }
        return add(model);
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
        MemberModel currentUser = UserCache.currentUser();
        if (currentUser.isSuperAdmin()) {
            return super.update(model);
        }
        model.setMember(currentUser);
        return super.update(model);
    }

    public MovieModel update(MovieModel model, MultipartFile log) throws Exception {
        if (log != null && !log.isEmpty()) {
            MovieModel before = findById(model.getId());
            if (!(MOVIE_IMG_PATH + log.getOriginalFilename()).equals(before.getImg())) {
                if (!(MOVIE_IMG_PATH + MOVIE_DEFAULT_IMG).equals(before.getImg())) {
                    File ago = new File(this.getClass().getResource("/").getFile() + before.getImg());
                    ago.delete();
                }
                File img = new File(this.getClass().getResource("/").getFile() + MOVIE_IMG_PATH + log.getOriginalFilename());
                log.transferTo(img);
                model.setImg(MOVIE_IMG_PATH + log.getOriginalFilename());
            }
        }
        return update(model);
    }
}

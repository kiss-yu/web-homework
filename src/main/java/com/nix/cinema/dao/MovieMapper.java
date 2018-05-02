package com.nix.cinema.dao;

import com.nix.cinema.dao.base.BaseMapper;
import com.nix.cinema.model.MovieModel;
import org.springframework.stereotype.Repository;

/**
 * @author Kiss
 * @date 2018/05/02 11:19
 */
@Repository
public interface MovieMapper extends BaseMapper<MovieModel> {
    /**
     *
     * */
    MovieModel updateCurrentUser(MovieModel model);
}

package com.nix.cinema.controller.member;

import com.nix.cinema.common.annotation.MemberController;
import com.nix.cinema.model.MovieModel;
import com.nix.cinema.service.impl.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kiss
 * @date 2018/05/10 21:18
 */
@MemberController
@RestController
@RequestMapping("/member/movie")
public class MemberMovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping("/hit")
    public void hit(Integer id) throws Exception{
        MovieModel model = movieService.findById(id);
        MovieModel newModel = new MovieModel();
        newModel.setId(model.getId());
        newModel.setHitCount(model.getHitCount() + 1);
        movieService.update(newModel);
    }
}

package com.nix.cinema.controller.admin;

import com.nix.cinema.Exception.WebException;
import com.nix.cinema.common.Pageable;
import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.AdminController;
import com.nix.cinema.common.cache.UserCache;
import com.nix.cinema.model.CinemaModel;
import com.nix.cinema.model.MemberModel;
import com.nix.cinema.model.MovieModel;
import com.nix.cinema.model.RoleModel;
import com.nix.cinema.service.impl.MemberService;
import com.nix.cinema.service.impl.MovieService;
import com.nix.cinema.service.impl.SystemService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kiss
 * @date 2018/05/02 10:37
 */
@RestController("adminMovieController")
@RequestMapping("/admin/movie")
@AdminController
public class AdminMovieController{

    @Autowired
    private MovieService movieService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    public ReturnObject createMovie(@ModelAttribute MovieModel movieModel,
                               @RequestParam(value = "logImg",required = false) MultipartFile log,
                               @RequestParam(value = "username",required = false) String username) throws Exception {
        MemberModel current = UserCache.currentUser();
        if (RoleModel.MOVIE_VALUE.equals(current.getRoleValue())) {
            movieModel.setMember(current);
        } else {
            MemberModel boss = memberService.findByUsername(username);
            movieModel.setMember(boss);
        }
        return ReturnUtil.success(movieService.add(movieModel,log));
    }
    @GetMapping("/checkMovieSn")
    public Boolean checkMovieSn(String movieSn) {
        if (movieSn == null || movieSn.isEmpty()) {
            return true;
        }
        return movieService.findByOneField("movieSn",movieSn) == null;
    }
    @GetMapping("/view")
    public ReturnObject select(@RequestParam("id") Integer id) {
        return ReturnUtil.success(movieService.findById(id));
    }
    @PostMapping("/delete")
    public ReturnObject delete(@RequestParam("ids") Integer[] ids) throws Exception {
        movieService.delete(ids);
        return ReturnUtil.success();
    }
    @PostMapping("/update")
    public ReturnObject update(@ModelAttribute MovieModel movieModel,
                                    @RequestParam(value = "logImg",required = false) MultipartFile log,
                                    @RequestParam(value = "username",required = false) String username) throws Exception {
        MemberModel current = UserCache.currentUser();
        if (RoleModel.MOVIE_VALUE.equals(current.getRoleValue())) {
            movieModel.setMember(current);
        } else {
            MemberModel boss = memberService.findByUsername(username);
            movieModel.setMember(boss);
        }
        return ReturnUtil.success(movieService.update(movieModel,log));
    }

    @PostMapping("/list")
    public ReturnObject list(@ModelAttribute Pageable<MovieModel> pageable) throws Exception {
        Map additionalData = new HashMap();
        MemberModel current = UserCache.currentUser();
        if (RoleModel.MOVIE_VALUE.equals(current.getRoleValue())) {
            pageable.setConditionsSql("member = " + current.getId());
        }
        List list = pageable.getList(movieService);
        additionalData.put("total",pageable.getCount());
        return ReturnUtil.success(null,list,additionalData);
    }
    @GetMapping("/autoMovie")
    public List autoCinema(@ModelAttribute Pageable pageable, @RequestParam("q") String movieSn) {
        MemberModel current = UserCache.currentUser();
        if (RoleModel.MOVIE_VALUE.equals(current.getRoleValue())) {
            pageable.setConditionsSql("(movieSn like '%" + movieSn + "%' or name like '%"  + movieSn + "%') and member = " +
            current.getId());
        } else {
            pageable.setConditionsSql("movieSn like '%" + movieSn + "%' or name like '%"  + movieSn + "%'");
        }
        return pageable.getList(movieService);
    }
}

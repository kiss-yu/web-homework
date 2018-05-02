package com.nix.cinema.controller.admin;

import com.nix.cinema.Exception.WebException;
import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.model.MovieModel;
import com.nix.cinema.service.impl.MovieService;
import com.nix.cinema.service.impl.SystemService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kiss
 * @date 2018/05/02 10:37
 */
@RestController
@RequestMapping("/admin/movie")
public class MovieController implements AdminController{

    @Autowired
    private MovieService movieService;
    @Autowired
    private SystemService systemService;
    @PostMapping("/add")
    public ReturnObject createMovie(@ModelAttribute MovieModel movieModel,
                                    @RequestParam("VCode")String VCode) throws Exception {
        if (!systemService.doCaptcha(VCode)) {
            throw new WebException(100,"验证码错误");
        }
        return ReturnUtil.success(movieService.add(movieModel));
    }
    @PostMapping("/delete")
    public ReturnObject delete(@RequestParam("ids") Integer[] ids) throws Exception {
        movieService.delete(ids);
        return ReturnUtil.success();
    }
    @PostMapping("/update")
    public ReturnObject update(@ModelAttribute MovieModel movieModel) throws Exception {
        return ReturnUtil.success(movieService.update(movieModel));
    }

}

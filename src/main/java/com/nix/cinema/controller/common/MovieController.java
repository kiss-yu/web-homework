package com.nix.cinema.controller.common;
import com.nix.cinema.common.Pageable;
import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.Clear;
import com.nix.cinema.model.MovieModel;
import com.nix.cinema.service.impl.MovieService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kiss
 * @date 2018/05/02 1:16
 */
@RestController()
@RequestMapping("/movie")
@Clear
public class MovieController {
    @Autowired
    private MovieService movieService;

    /**
     * 查看电影详情
     * */
    @GetMapping("/{id}")
    public ReturnObject movie(@PathVariable("id") Integer id) {
        return ReturnUtil.success(movieService.findById(id));
    }
    /**
     * 查看电影列表
     * */
    @PostMapping("/list")
    public ReturnObject list(@ModelAttribute Pageable<MovieModel> pageable) {
        Map additionalData = new HashMap();
        List list = pageable.getList(movieService);
        additionalData.put("total",pageable.getCount());
        return ReturnUtil.success(null,list,additionalData);
    }
}

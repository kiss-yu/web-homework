package com.nix.cinema.controller.common;

import com.nix.cinema.common.Pageable;
import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.model.CinemaModel;
import com.nix.cinema.model.MovieModel;
import com.nix.cinema.service.impl.CinemaService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kiss
 * @date 2018/05/10 16:40
 */
@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;
    @PostMapping("/list")
    public ReturnObject list(@ModelAttribute Pageable<CinemaModel> pageable) {
        Map additionalData = new HashMap();
        List list = pageable.getList(cinemaService);
        additionalData.put("total",list.size());
        return ReturnUtil.success(null,list,additionalData);
    }
}

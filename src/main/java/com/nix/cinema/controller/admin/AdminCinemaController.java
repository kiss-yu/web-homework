package com.nix.cinema.controller.admin;

import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.AdminController;
import com.nix.cinema.model.CinemaModel;
import com.nix.cinema.service.impl.CinemaService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kiss
 * @date 2018/05/02 20:30
 */
@RestController
@AdminController
@RequestMapping("/admin/cinema")
public class AdminCinemaController {
    @Autowired
    private CinemaService cinemaService;

    @PostMapping("/add")
    public ReturnObject create(@ModelAttribute CinemaModel cinema) throws Exception {
        return ReturnUtil.success(cinemaService.add(cinema));
    }
}

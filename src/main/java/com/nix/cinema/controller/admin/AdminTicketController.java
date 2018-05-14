package com.nix.cinema.controller.admin;

import com.nix.cinema.Exception.WebException;
import com.nix.cinema.common.Pageable;
import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.AdminController;
import com.nix.cinema.common.cache.UserCache;
import com.nix.cinema.model.*;
import com.nix.cinema.service.impl.CinemaService;
import com.nix.cinema.service.impl.MovieService;
import com.nix.cinema.service.impl.TicketService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Kiss
 * @date 2018/05/08 18:54
 */
@AdminController
@RestController
@RequestMapping("/admin/ticket")
public class AdminTicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private MovieService movieService;
    @PostMapping("/add")
    public ReturnObject add(@ModelAttribute TicketModel ticketModel,
                            @RequestParam(value = "cinemaSn",required = false) String cinemaSn,
                            @RequestParam(value = "movieSn",required = false) String movieSn) throws Exception {
        MemberModel current = UserCache.currentUser();
        if (cinemaSn != null && !cinemaSn.isEmpty()) {
            List<CinemaModel> cinemaModels = cinemaService.findByOneField("cinemaSn",cinemaSn);
            if (cinemaModels.size() < 1) {
                throw new WebException(100,"影院编号错误");
            }
            CinemaModel cinemaModel = cinemaModels.get(0);
            if (RoleModel.CINEMA_VALUE.equals(current.getRole().getValue())) {
                if (!cinemaModel.getMember().getId().equals(current.getId())) {
                    throw new WebException(401,"权限不足");
                }
            }
            ticketModel.setCinema(cinemaModels.get(0));
            ticketModel.setMember(cinemaModels.get(0).getMember());
        }
        if (movieSn != null && !movieSn.isEmpty()) {
            List<MovieModel> movieModels = movieService.findByOneField("movieSn",movieSn);
            if (movieModels.size() < 1) {
                throw new WebException(100,"电影编号错误");
            }
            ticketModel.setMovie(movieModels.get(0));
        }
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.getAndIncrement();
        return ReturnUtil.success(ticketService.add(ticketModel));
    }
    @GetMapping("/view")
    public ReturnObject select(@RequestParam("id") Integer id) {
        return ReturnUtil.success(ticketService.findById(id));
    }
    @PostMapping("/update")
    public ReturnObject update(@ModelAttribute TicketModel ticketModel,
                            @RequestParam(value = "cinemaSn",defaultValue = "") String cinemaSn,
                            @RequestParam(value = "movieSn",defaultValue = "") String movieSn) throws Exception {
        MemberModel current = UserCache.currentUser();
        if (cinemaSn.isEmpty()) {
            List<CinemaModel> cinemaModels = cinemaService.findByOneField("cinemaSn",cinemaSn);
            if (cinemaModels.size() < 1) {
                throw new WebException(100,"影院编号错误");
            }
            CinemaModel cinemaModel = cinemaModels.get(0);
            if (RoleModel.CINEMA_VALUE.equals(current.getRole().getValue())) {
                if (!cinemaModel.getMember().getId().equals(current.getId())) {
                    throw new WebException(401,"权限不足");
                }
            }
            ticketModel.setCinema(cinemaModel);
        }
        if (movieSn.isEmpty()) {
            List<MovieModel> movieModels = movieService.findByOneField("movieSn",movieSn);
            if (movieModels.size() < 1) {
                throw new WebException(100,"电影编号错误");
            }
            ticketModel.setMovie(movieModels.get(0));
        }
        return ReturnUtil.success(ticketService.update(ticketModel));
    }
    @PostMapping("/delete")
    public ReturnObject delete(@RequestParam("ids") Integer[] ids) throws Exception {
        ticketService.delete(ids);
        return ReturnUtil.success();
    }
    @PostMapping("/list")
    public ReturnObject list(@ModelAttribute Pageable<TicketModel> pageable) throws Exception {
        Map additionalData = new HashMap();
        MemberModel current = UserCache.currentUser();
        if (RoleModel.CINEMA_VALUE.equals(current.getRoleValue())) {
            pageable.setConditionsSql("member = " + current.getId());
        }
        List list = pageable.getList(ticketService);
        additionalData.put("total",pageable.getCount());
        return ReturnUtil.success(null,list,additionalData);
    }
}

package com.nix.cinema.controller.member;

import com.nix.cinema.common.Pageable;
import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.Clear;
import com.nix.cinema.common.annotation.MemberController;
import com.nix.cinema.model.MovieModel;
import com.nix.cinema.model.TicketModel;
import com.nix.cinema.service.impl.MovieService;
import com.nix.cinema.service.impl.TicketService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private TicketService ticketService;
    @GetMapping("/hit")
    @Clear
    public void hit(Integer id) throws Exception{
        MovieModel model = movieService.findById(id);
        MovieModel newModel = new MovieModel();
        newModel.setId(model.getId());
        newModel.setHitCount(model.getHitCount() + 1);
        movieService.update(newModel);
    }
    /**
     * 获取电影播放的电影院和可以购买的票
     * */
    @PostMapping("/ticket")
    public ReturnObject tickets(@ModelAttribute Pageable<TicketModel> pageable,
                                @RequestParam("id") Integer id) {
        pageable.setConditionsSql("movie = " + id);
        List<TicketModel> list = pageable.getList(ticketService);
        Map additionalData = new HashMap();
        additionalData.put("total",list.size());
        return ReturnUtil.success(null,list,additionalData);
    }
}

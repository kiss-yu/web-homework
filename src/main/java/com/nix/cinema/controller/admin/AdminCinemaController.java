package com.nix.cinema.controller.admin;

import com.nix.cinema.common.Pageable;
import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.AdminController;
import com.nix.cinema.model.CinemaModel;
import com.nix.cinema.model.MemberModel;
import com.nix.cinema.model.RoleModel;
import com.nix.cinema.service.impl.CinemaService;
import com.nix.cinema.service.impl.MemberService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    public ReturnObject create(@ModelAttribute CinemaModel cinema,
                               @RequestParam(value = "logImg",required = false) MultipartFile log,
                               @RequestParam(value = "username",required = false) String username) throws Exception {
        MemberModel boss = memberService.findByUsername(username);
        cinema.setMember(boss);
        return ReturnUtil.success(cinemaService.add(cinema,log));
    }
    @GetMapping("/checkUsername")
    public Boolean checkUsername(String username) {
        if (username == null || username.isEmpty()) {
            return true;
        }
        return memberService.findByUsername(username) != null;
    }
    @GetMapping("/checkCinemaSn")
    public Boolean checkCinemaSn(String cinemaSn) {
        if (cinemaSn == null || cinemaSn.isEmpty()) {
            return true;
        }
        return cinemaService.findByOneField("cinemaSn",cinemaSn) == null;
    }
    @GetMapping("/view")
    public ReturnObject select(@RequestParam("id") Integer id) {
        return ReturnUtil.success(cinemaService.findById(id));
    }

    @PostMapping("/update")
    public ReturnObject update(@ModelAttribute CinemaModel cinema,
                               @RequestParam(value = "logImg",required = false) MultipartFile log,
                               @RequestParam(value = "username",required = false) String username) throws Exception {
        MemberModel boss = memberService.findByUsername(username);
        cinema.setMember(boss);
        if (log != null) {
            return ReturnUtil.success(cinemaService.update(cinema, log));
        } else {
            return ReturnUtil.success(cinemaService.update(cinema));
        }
    }
    @PostMapping("/delete")
    public ReturnObject delete(@RequestParam("ids") Integer[] ids) throws Exception {
        cinemaService.delete(ids);
        return ReturnUtil.success();
    }

    @PostMapping("/list")
    public ReturnObject list(@ModelAttribute Pageable<CinemaModel> pageable) throws Exception {
        Map additionalData = new HashMap();
        List list = pageable.getList(cinemaService);
        additionalData.put("total",pageable.getCount());
        return ReturnUtil.success(null,list,additionalData);
    }
    @GetMapping("/autoCinema")
    public List autoCinema(@ModelAttribute Pageable pageable, @RequestParam("q") String cinemaSn) {
        pageable.setConditionsSql("cinemaSn like '%" + cinemaSn + "%'");
        return pageable.getList(cinemaService);
    }
}

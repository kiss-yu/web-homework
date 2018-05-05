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
                               @RequestParam(value = "log",required = false) MultipartFile log,
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
    @PostMapping("/list")
    public ReturnObject list(@ModelAttribute Pageable<CinemaModel> pageable) throws Exception {
        Map additionalData = new HashMap();
        additionalData.put("total",cinemaService.count());
        return ReturnUtil.success(null,pageable.getList(cinemaService),additionalData);
    }
    @GetMapping("/autoMember")
    public List autoMember(@ModelAttribute Pageable pageable, @RequestParam("q") String username) {
        pageable.setConditionsSql("username like '%" + username + "%'");
        return pageable.getList(memberService);
    }
}

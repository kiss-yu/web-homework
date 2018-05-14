package com.nix.cinema.controller.member;

import com.alibaba.fastjson.JSONObject;
import com.nix.cinema.common.Pageable;
import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.MemberController;
import com.nix.cinema.common.cache.UserCache;
import com.nix.cinema.model.CinemaModel;
import com.nix.cinema.model.MemberModel;
import com.nix.cinema.model.PaymentModel;
import com.nix.cinema.model.TicketModel;
import com.nix.cinema.service.impl.CinemaService;
import com.nix.cinema.service.impl.MemberService;
import com.nix.cinema.service.impl.PaymentService;
import com.nix.cinema.service.impl.TicketService;
import com.nix.cinema.util.ReturnUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kiss
 * @date 2018/05/10 22:02
 */
@MemberController
@RestController
@RequestMapping("/member/payment")
public class MemberPaymentController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CinemaService cinemaService;
    /**
     * 支付并创建订单
     * */
    @PostMapping("/payment")
    public ReturnObject createPayment(@RequestParam(value = "ticketIds") Integer[] ticketIds,
                                      @RequestParam(value = "indexes") Integer[][] indexes) throws Exception {
        MemberModel current = UserCache.currentUser();
        Assert.notNull(current,"非法订单");
        Assert.isTrue(ticketIds.length == indexes.length || ticketIds.length == 1,"无效订单");
        Assert.isTrue(ticketIds.length > 0,"没有添加电影票，无效订单");
        float price = 0;
        StringBuilder content = new StringBuilder("{");
        if (ticketIds.length == 1) {
            TicketModel ticketModel = ticketService.findById(ticketIds[0]);
            StringBuilder ticketSell = new StringBuilder(ticketModel.getSellSn() == null || ticketModel.getSellSn().isEmpty() ?
            "" : ticketModel.getSellSn() + ",");
            CinemaModel cinemaModel = ticketModel.getCinema();
            cinemaModel.setHotNumber(cinemaModel.getHotNumber() + 1);
            cinemaService.update(cinemaModel);
            price += (ticketModel.getPrice().floatValue() * indexes[0].length);
            content.append(ticketIds[0] + ":[");
            for (Integer[] index : indexes) {
                content.append(index[0] + ",");
                ticketSell.append(index[0] + ",");
            }
            content.replace(content.length() - 1, content.length(), "],");
            ticketModel.setSellSn(ticketSell.toString().substring(0,ticketSell.length() - 1));
            ticketService.update(ticketModel);

        } else {
            for (int i = 0; i < ticketIds.length; i++) {
                TicketModel ticketModel = ticketService.findById(ticketIds[i]);
                StringBuilder ticketSell = new StringBuilder(ticketModel.getSellSn() == null || ticketModel.getSellSn().isEmpty() ?
                        "" : ticketModel.getSellSn() + ",");
                CinemaModel cinemaModel = ticketModel.getCinema();
                cinemaModel.setHotNumber(cinemaModel.getHotNumber() + 1);
                cinemaService.update(cinemaModel);
                price += (ticketModel.getPrice().floatValue() * indexes[i].length);
                content.append(ticketIds[i] + ":[");
                for (Integer index : indexes[i]) {
                    content.append(index + ",");
                    ticketSell.append(index + ",");
                }
                content.replace(content.length() - 1, content.length(), "],");
                ticketModel.setSellSn(ticketSell.toString().substring(0,ticketSell.length() - 1));
                ticketService.update(ticketModel);
            }
        }
        BigDecimal balance = current.getBalance().subtract(new BigDecimal(price));
        if (balance.floatValue() < 0) {
            return ReturnUtil.fail(100,"余额不足",current.getBalance().floatValue());
        }
        content.replace(content.length() - 1,content.length(),"}");
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setSn(paymentModel.generateSn());
        paymentModel.setMember(current);
        paymentModel.setAmount(new BigDecimal(price));
        paymentModel.setContent(content.toString());
        paymentService.add(paymentModel);
        current.setBalance(balance);
        memberService.update(current);
        return ReturnUtil.success();
    }
    @PostMapping("/list")
    public ReturnObject list(@ModelAttribute Pageable<PaymentModel> pageable) throws Exception {
        Map additionalData = new HashMap();
        MemberModel current = UserCache.currentUser();
        pageable.setConditionsSql("member = " + current.getId());
        List<PaymentModel> list = pageable.getList(paymentService);
        if (list != null) {
            for (PaymentModel paymentModel:list) {
                if (paymentModel.getContent() != null) {
                    JSONObject jsonObject = JSONObject.parseObject(paymentModel.getContent());
                    Integer ticketId = Integer.valueOf(jsonObject.keySet().iterator().next());
                    paymentModel.setTicket(ticketService.findById(ticketId));
                }
            }
            additionalData.put("total", pageable.getCount());
            return ReturnUtil.success(null, list, additionalData);
        }
        return ReturnUtil.success();
    }
}

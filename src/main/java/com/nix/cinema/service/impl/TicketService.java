package com.nix.cinema.service.impl;

import com.nix.cinema.model.TicketModel;
import com.nix.cinema.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * @author Kiss
 * @date 2018/05/01 22:57
 * 电影票Service
 */
@Service
public class TicketService extends BaseService<TicketModel> {
    @Override
    public TicketModel add(TicketModel model) throws Exception {
        model.setTicketSn(model.getTicketSn() == null || model.getTicketSn().isEmpty() ? model.generateSn():model.getTicketSn());
        return super.add(model);
    }
}

package com.nix.cinema.service.impl;

import com.nix.cinema.model.CinemaModel;
import com.nix.cinema.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Kiss
 * @date 2018/05/01 22:52
 * 电影院service
 */
@Service
public class CinemaService extends BaseService<CinemaModel> {
    public final static String CINEMA_IMG_PATH = "/images/cinema/";
    public final static String CINEMA_DEFAULT_IMG = "default.jpg";
    public CinemaModel add(CinemaModel model, MultipartFile log) throws Exception {
        if (log != null && !log.isEmpty()) {
            File img = new File(this.getClass().getResource("/").getFile() + CINEMA_IMG_PATH + log.getOriginalFilename());
            log.transferTo(img);
            model.setLog(CINEMA_IMG_PATH + log.getOriginalFilename());
        } else {
            model.setLog(CINEMA_IMG_PATH + CINEMA_DEFAULT_IMG);
        }
        return add(model);
    }

    public CinemaModel update(CinemaModel model, MultipartFile log) throws Exception {
        if (log != null && !log.isEmpty()) {
            CinemaModel before = findByOneField("cinemaSn",model.getCinemaSn()).get(0);
            if (!(CINEMA_IMG_PATH + log.getOriginalFilename()).equals(before.getLog())) {
                if (!(CINEMA_IMG_PATH + CINEMA_DEFAULT_IMG).equals(before.getLog())) {
                    File ago = new File(this.getClass().getResource("/").getFile() + before.getLog());
                    ago.delete();
                }
                File img = new File(this.getClass().getResource("/").getFile() + CINEMA_IMG_PATH + log.getOriginalFilename());
                log.transferTo(img);
                model.setLog(CINEMA_IMG_PATH + log.getOriginalFilename());
            }
        }
        return update(model);
    }
    @Override
    public CinemaModel add(CinemaModel model) throws Exception {
        model.setCinemaSn(model.getCinemaSn() == null || model.getCinemaSn().isEmpty() ? model.generateSn() : model.getCinemaSn());
        return super.add(model);
    }
}

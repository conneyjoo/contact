package com.jinshun.contact.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.jinshun.contact.auth.Access;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.entity.License;
import com.jinshun.contact.entity.Seal;
import com.jinshun.contact.service.LicenseService;
import com.jinshun.contact.service.SealService;
import com.jinshun.contact.utils.DateUtils;
import com.jinshun.contact.utils.model.LicenseQueryModel;
import com.jinshun.contact.utils.model.SealQueryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/seal")
public class SealController extends ControllerSupport {

    private Logger LOGGER = LoggerFactory.getLogger(SealController.class);

    @Autowired
    private SealService sealService;

    @RequestMapping("saveOrUpdate")
    @Access()
    public @ResponseBody
    Message saveOrUpdateBid(Seal seal) {
        Message message = new Message();
        seal.setCompany(getCurrentCompany());
        try {
            message.setData(sealService.saveOrUpdate(seal));
            message.setSuccess(true);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            message.setSuccess(false);
        }
        return message;
    }

    @Access()
    @RequestMapping("remove")
    public @ResponseBody
    Message remove(Long id) {
        Message message = new Message();
        try {
            sealService.delete(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            message.setSuccess(false);
        }
        return message;
    }

    @RequestMapping("getById")
    @Access()
    public @ResponseBody
    Seal getById(Long id) {
        if (id == null) {
            LOGGER.error("ID不能为空!!");
            return null;
        }
        return sealService.getById(id);
    }

    @Access()
    @RequestMapping("findSeals")
    public @ResponseBody
    List<?> findSeals(SealQueryModel model, Integer curPage, Integer pageSize, String sort, String direction) {
        model.setCompanyId(getCurrentCompany().getId());
        List<?> objs = sealService.querySeals(model, curPage, pageSize, sort, direction);
        return objs;
    }

}

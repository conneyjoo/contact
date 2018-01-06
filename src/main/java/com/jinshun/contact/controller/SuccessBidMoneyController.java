package com.jinshun.contact.controller;

import com.jinshun.contact.auth.Access;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.entity.SuccessBidMoney;
import com.jinshun.contact.service.SuccessBidMoneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/successbidmoney")
public class SuccessBidMoneyController extends ControllerSupport {

    private Logger logger = LoggerFactory.getLogger(SuccessBidMoneyController.class);

    @Autowired
    private SuccessBidMoneyService successBidMoneyService;

    @Access()
    @RequestMapping("findSuccessBidMoney")
    public @ResponseBody List<?> findSuccessBidMoney(SuccessBidMoney successBidMoney) {
        return successBidMoneyService.findSuccessBidMoney(successBidMoney);
    }

    @Access()
    @RequestMapping("save")
    public @ResponseBody Message save(SuccessBidMoney successBidMoney) {
        Message message = new Message();

        try {
            successBidMoney.setCreator(getCurrentUser().getName());
            message.setData(successBidMoneyService.save(successBidMoney));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message.setSuccess(false);
        }

        return message;
    }

    @Access()
    @RequestMapping("remove")
    public @ResponseBody Message remove(SuccessBidMoney successBidMoney) {
        Message message = new Message();

        try {
            successBidMoneyService.remove(successBidMoney);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message.setSuccess(false);
        }

        return message;
    }
}

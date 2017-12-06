package com.jinshun.contact.controller;

import com.jinshun.contact.auth.Access;
import com.jinshun.contact.auth.Authorities;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.entity.SuccessBid;
import com.jinshun.contact.entity.User;
import com.jinshun.contact.service.SuccessBidService;
import com.jinshun.contact.utils.DateUtils;
import com.jinshun.contact.utils.model.BidQueryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/successbid")
public class SuccessBidController extends ControllerSupport {

    private Logger logger = LoggerFactory.getLogger(SuccessBidController.class);

    @Autowired
    private SuccessBidService successBidService;

    @Access()
    @RequestMapping("findSuccessBid")
    public @ResponseBody List<?> findSuccessBid(SuccessBid successBid, Integer curPage, Integer pageSize, String sort, String direction) {
        successBid.setCompany(getCurrentCompany());
        return successBidService.findSuccessBid(successBid, curPage, pageSize, sort, direction);
    }

    @Access()
    @RequestMapping("save")
    public @ResponseBody Message save(SuccessBid successBid) {
        Message message = new Message();

        try {
            successBid.setCompany(getCurrentCompany());
            if(successBid.getCreator()==null)
                successBid.setCreator(getCurrentUser().getName());
            message.setData(successBidService.save(successBid));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message.setSuccess(false);
        }

        return message;
    }

    @Access()
    @RequestMapping("remove")
    public @ResponseBody Message remove(SuccessBid successBid) {
        Message message = new Message();

        try {
            successBidService.remove(successBid);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message.setSuccess(false);
        }

        return message;
    }

    @Access()
    @RequestMapping("updateInWarehouse")
    public @ResponseBody Message updateInWarehouse(SuccessBid successBid) {
        Message message = new Message();

        try {
            successBidService.updateInWarehouse(successBid);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message.setSuccess(false);
        }

        return message;
    }
}

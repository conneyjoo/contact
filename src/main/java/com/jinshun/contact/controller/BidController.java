package com.jinshun.contact.controller;

import com.jinshun.contact.auth.Access;
import com.jinshun.contact.auth.Authorities;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.controller.sys.MenuController;
import com.jinshun.contact.entity.Bid;
import com.jinshun.contact.entity.User;
import com.jinshun.contact.service.BidService;
import com.jinshun.contact.utils.DateUtils;
import com.jinshun.contact.utils.model.BidQueryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/bid")
public class BidController extends ControllerSupport {

    private Logger LOGGER = LoggerFactory.getLogger(BidController.class);

    @Autowired
    private BidService bidService;

    @RequestMapping("saveOrUpdate")
    @Access()
    public @ResponseBody
    Bid saveOrUpdateBid(Bid bid) {
        if (bid == null) {
            LOGGER.error("对象不能为空！");
            return null;
        }
        if (bid.getInWarehouse() == null)
            bid.setInWarehouse(0);
        bid.setCompany(getCurrentCompany());
        return bidService.saveOrUpdate(bid);
    }

    @RequestMapping("submit")
    @Access()
    public @ResponseBody
    Message submit(Long id) {
        Message message = new Message();
        try {
            Bid bid = bidService.getById(id);
            bid.setInWarehouse(1);
            bidService.saveOrUpdate(bid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            message.setSuccess(false);
        }
        return message;
    }

    @Access(authorities = Authorities.LOGIN)
    @RequestMapping("remove")
    public @ResponseBody
    Message deleteBid(Long id) {
        Message message = new Message();
        try {
            bidService.delete(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            message.setSuccess(false);
        }
        return message;
    }

    @RequestMapping("getById")
    @Access()
    public @ResponseBody
    Bid getById(Long id) {
        if (id == null) {
            LOGGER.error("ID不能为空!!");
            return null;
        }
        return bidService.getById(id);
    }

    @Access(authorities = Authorities.LOGIN)
    @RequestMapping("findBids")
    public @ResponseBody
    List<?> findBids(BidQueryModel model, Integer curPage, Integer pageSize, String sort, String direction) {
        model.setCompanyId(getCurrentCompany().getId());
        List<?> objs = bidService.queryBids(model, curPage, pageSize, sort, direction);
        for(Object obj : objs){
            HashMap map = (HashMap) obj;
            if(map.get("depositReturnTime")==null){
                Date returnTime = (Date) map.get("depositRemitTime");
                int intervalDays = DateUtils.getIntervalDays(new Date(),returnTime);
                map.put("warning",intervalDays>30?true:false);
            }
        }
        return objs;
    }

}

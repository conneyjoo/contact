package com.jinshun.contact.controller;

import com.jinshun.contact.auth.Access;
import com.jinshun.contact.auth.Authorities;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.controller.sys.MenuController;
import com.jinshun.contact.entity.Bid;
import com.jinshun.contact.entity.SuccessBid;
import com.jinshun.contact.entity.User;
import com.jinshun.contact.service.BidService;
import com.jinshun.contact.service.SuccessBidService;
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

    @Autowired
    private SuccessBidService successBidService;

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

        if(bid.getFirstApplied() == null)
            bid.setFirstApplied(0);

        if(bid.getStatus() == null)
            bid.setStatus(0);

        if(bid.getId() ==null){
            bid.setCreator(getCurrentUser().getName());
        }else if(bid.getPrincipal() == null || bid.getPrincipal().length()==0){
            bid.setPrincipal(getCurrentUser().getName());
        }

        //如果是第一次置为中标
        if(bid.getBidOpenResult() == 1){
            if(bid.getFirstApplied()==0){
                SuccessBid successBid = new SuccessBid();
                successBid.setCompany(getCurrentCompany());
                successBid.setName(bid.getName());
                successBid.setArea(bid.getArea());
                successBid.setType(bid.getType());
                successBid.setConstructer(bid.getConstructor());
                successBid.setInWarehouse(0);
                successBid.setCreator(bid.getCreator());
                successBidService.save(successBid);
                bid.setFirstApplied(1);
            }
        }
        bid.setCompany(getCurrentCompany());
        if(bid.getFirstApplied() == null)
            bid.setFirstApplied(0);
        return bidService.saveOrUpdate(bid);
    }

    @RequestMapping("getFirstApplied")
    @Access()
    public @ResponseBody
    Message getFirstApplied(Long id) {
        Message message = new Message();
        try {
            Bid bid = bidService.getById(id);
            message.setData(bid.getFirstApplied());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            message.setSuccess(false);
        }
        return message;
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

    @RequestMapping("withdraw")
    @Access()
    public @ResponseBody
    Message withdraw(Long id) {
        Message message = new Message();
        try {
            Bid bid = bidService.getById(id);
            bid.setInWarehouse(0);
            bidService.saveOrUpdate(bid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            message.setSuccess(false);
        }
        return message;
    }

    @Access()
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

    @Access()
    @RequestMapping("findBids")
    public @ResponseBody
    List<?> findBids(BidQueryModel model, Integer curPage, Integer pageSize, String sort, String direction) {
        model.setCompanyId(getCurrentCompany().getId());
        List<?> objs = bidService.queryBids(model, curPage, pageSize, sort, direction , 0);
        for(Object obj : objs){
            HashMap map = (HashMap) obj;
            if(map.get("depositReturnTime")==null && map.get("depositRemitTime")!=null){
                Date returnTime = (Date) map.get("depositRemitTime");
                int intervalDays = DateUtils.getIntervalDays(new Date(),returnTime);
                map.put("warning",intervalDays>30?true:null);
            }
        }
        return objs;
    }

    @Access()
    @RequestMapping("findBidsForWarehouse")
    public @ResponseBody
    List<?> findBidsForWarehouse(BidQueryModel model, Integer curPage, Integer pageSize, String sort, String direction) {
        model.setCompanyId(getCurrentCompany().getId());
        List<?> objs = bidService.queryBids(model, curPage, pageSize, sort, direction , 1);
        for(Object obj : objs){
            HashMap map = (HashMap) obj;
            if(map.get("depositReturnTime")==null && map.get("depositRemitTime")!=null){
                Date returnTime = (Date) map.get("depositRemitTime");
                int intervalDays = DateUtils.getIntervalDays(new Date(),returnTime);
                map.put("warning",intervalDays>30?true:null);
            }
        }
        return objs;
    }

}

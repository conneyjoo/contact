package com.jinshun.contact.controller;

import com.jinshun.contact.auth.Access;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.controller.sys.MenuController;
import com.jinshun.contact.entity.Bid;
import com.jinshun.contact.service.BidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/bid")
public class BidController {

    private Logger LOGGER = LoggerFactory.getLogger(BidController.class);

    @Autowired
    private BidService bidService;

    @RequestMapping("saveOrUpdate")
    @Access()
    public @ResponseBody Bid saveOrUpdateBid(Bid bid){
        if(bid==null){
            LOGGER.error("对象不能为空！");
            return null;
        }
         return bidService.saveOrUpdate(bid);
    }

    @RequestMapping("delete")
    @Access()
    public @ResponseBody void deleteBid(Long id){
        if(id==null){
            LOGGER.error("ID不能为空！");
            return;
        }
        bidService.delete(id);
    }

    @RequestMapping("getById")
    @Access()
    public @ResponseBody Bid getById(Long id){
        if(id==null){
            LOGGER.error("ID不能为空！");
            return null;
        }
       return  bidService.getById(id);
    }



     
}

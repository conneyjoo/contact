package com.jinshun.contact.controller;

import com.jinshun.contact.auth.Access;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.entity.Credit;
import com.jinshun.contact.service.CreditService;
import com.jinshun.contact.utils.model.CreditQueryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/credit")
public class CreditController extends ControllerSupport {

    private Logger LOGGER = LoggerFactory.getLogger(CreditController.class);

    @Autowired
    private CreditService creditService;

    @RequestMapping("saveOrUpdate")
    @Access()
    public @ResponseBody
    Message saveOrUpdateBid(Credit credit) {
        Message message = new Message();
        credit.setCompany(getCurrentCompany());
        try {
            message.setData(creditService.saveOrUpdate(credit));
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
            creditService.delete(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            message.setSuccess(false);
        }
        return message;
    }

    @RequestMapping("getById")
    @Access()
    public @ResponseBody
    Credit getById(Long id) {
        if (id == null) {
            LOGGER.error("ID不能为空!!");
            return null;
        }
        return creditService.getById(id);
    }

    @Access()
    @RequestMapping("findCredits")
    public @ResponseBody
    List<?> findCredits(CreditQueryModel model, Integer curPage, Integer pageSize, String sort, String direction) {
        model.setCompanyId(getCurrentCompany().getId());
        List<?> objs = creditService.queryCredit(model, curPage, pageSize, sort, direction);
        return objs;
    }


}

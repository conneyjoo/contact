package com.jinshun.contact.controller;

import com.jinshun.contact.auth.Access;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.entity.License;
import com.jinshun.contact.service.LicenseService;
import com.jinshun.contact.utils.DateUtils;
import com.jinshun.contact.utils.model.LicenseQueryModel;
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
@RequestMapping("/license")
public class LicenseController extends ControllerSupport {

    private Logger LOGGER = LoggerFactory.getLogger(LicenseController.class);

    @Autowired
    private LicenseService licenseService;

    @RequestMapping("saveOrUpdate")
    @Access()
    public @ResponseBody
    Message saveOrUpdateBid(License license) {
        Message message = new Message();
        license.setCompany(getCurrentCompany());
        try {
            message.setData(licenseService.saveOrUpdate(license));
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
            licenseService.delete(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            message.setSuccess(false);
        }
        return message;
    }

    @RequestMapping("getById")
    @Access()
    public @ResponseBody
    License getById(Long id) {
        if (id == null) {
            LOGGER.error("ID不能为空!!");
            return null;
        }
        return licenseService.getById(id);
    }

    @Access()
    @RequestMapping("findLicenses")
    public @ResponseBody
    List<?> findLicenses(LicenseQueryModel model, Integer curPage, Integer pageSize, String sort, String direction) {
        model.setCompanyId(getCurrentCompany().getId());
        List<?> objs = licenseService.queryLicenses(model, curPage, pageSize, sort, direction);
        for (Object obj : objs) {
            HashMap map = (HashMap) obj;
            if (map.get("validDate") != null) {
                Date validDate = (Date) map.get("validDate");
                int intervalDays = DateUtils.getIntervalDays(new Date(), validDate);
                map.put("warning", intervalDays < 90 ? false : true);
            }
        }
        return objs;
    }

}

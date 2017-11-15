package com.jinshun.contact.controller.sys;

import com.jinshun.contact.auth.Access;
import com.jinshun.contact.auth.Authorities;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.entity.Company;
import com.jinshun.contact.service.sys.CompanyService;
import com.jinshun.contact.service.sys.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/company")
public class CompanyController extends ControllerSupport {

    private Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @RequestMapping("findAll")
    @Access(authorities = Authorities.LOGIN)
    public @ResponseBody Iterable<Company> findAll() {
        return companyService.findAll();
    }

    @RequestMapping("select")
    @Access(authorities = Authorities.LOGIN)
    public @ResponseBody Message select(Long id) {
        setCurrentCompany(companyService.get(id));
        return SUCCESS;
    }
}

package com.jinshun.contact.controller.sys;

import com.jinshun.contact.auth.Access;
import com.jinshun.contact.auth.Authorities;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.entity.Menu;
import com.jinshun.contact.service.sys.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController extends ControllerSupport {

    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping("getMenus")
    @Access(authorities = Authorities.LOGIN)
    public @ResponseBody Message getMenus() {
        Message message = new Message();

        try {
            message.setData(menuService.getMenus(getCurrentUser().getRole().getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return message;
    }

    @RequestMapping("findMenu")
    @Access(authorities = Authorities.LOGIN)
    public @ResponseBody List<?> findMenu(Menu menu) {
        return menuService.findMenu(menu);
    }
}

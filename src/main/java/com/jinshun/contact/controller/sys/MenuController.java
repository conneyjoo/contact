package com.jinshun.contact.controller.sys;

import com.jinshun.contact.controller.common.Access;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.entity.User;
import com.jinshun.contact.service.sys.MenuService;
import com.jinshun.contact.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/menu")
public class MenuController extends ControllerSupport {

    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping("getMenus")
    @Access()
    public @ResponseBody Message getMenus() {
        Message message = new Message();

        try {
            message.setData(menuService.getMenus(getCurrentUser().getRole().getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return message;
    }
}

package com.jinshun.contact.controller.sys;

import com.jinshun.contact.auth.Access;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.entity.Role;
import com.jinshun.contact.service.sys.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController extends ControllerSupport {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Access()
    @RequestMapping("findRole")
    public @ResponseBody List<?> findRole(Role role) {
        return roleService.findRole(role);
    }

    @Access()
    @RequestMapping("save")
    public @ResponseBody
    ControllerSupport.Message save(Role role, @RequestParam(value = "menuIds[]") String[] menuIds/*, @RequestParam(value = "actionIds[]") String[] actionIds*/) {
        Message message = new Message();

        try {
            message.setData(roleService.save(role, menuIds, null));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message.setSuccess(false);
        }

        return message;
    }

    @Access()
    @RequestMapping("remove")
    public @ResponseBody ControllerSupport.Message remove(Role role) {
        ControllerSupport.Message message = new ControllerSupport.Message();

        try {
            roleService.remove(role);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message.setSuccess(false);
        }

        return message;
    }

    @RequestMapping("getMenus")
    @Access()
    public @ResponseBody List<?> getMenus(Long id) {
        return roleService.getMenus(id);
    }

    @RequestMapping("getActions")
    @Access()
    public @ResponseBody List<?> getActions(Long id) {
        return roleService.getActions(id);
    }
}

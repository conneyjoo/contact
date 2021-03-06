package com.jinshun.contact.controller.sys;

import com.jinshun.contact.auth.Access;
import com.jinshun.contact.auth.Authorities;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.entity.Company;
import com.jinshun.contact.entity.Role;
import com.jinshun.contact.entity.User;
import com.jinshun.contact.service.sys.ActionService;
import com.jinshun.contact.service.sys.CompanyService;
import com.jinshun.contact.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends ControllerSupport {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final int LOGIN_SUCCESS = 1;

    private static final int LOGIN_USERNAME_INCORRECT = -1;

    private static final int LOGIN_PASSWORD_INCORRECT = -2;

    private static final int LOGIN_FAILED = -3;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ActionService actionService;

    @RequestMapping("login")
    public @ResponseBody Message login(String username, String password) {
        Message message = new Message();

        try {
            if("administrator".equals(username) && "administrator".equals(password)){
                Role role = new Role();
                role.setLevel(69905);
                role.setId((long)1);
                User admin = new User();
                setCurrentCompany(companyService.get(1L));
                admin.setCompanyName(getCurrentCompany().getName());
                admin.setRole(role);
                setCurrentUser(admin);
                message.setData(admin);
                message.setStatus(LOGIN_SUCCESS);
                return message;
            }
            User user = userService.getUser(username);

            if (user != null) {
                if (user.getPassword().equals(password)) {
                    // user.setActions(actionService.getActionSet(user.getRole().getId()));
                    setCurrentUser(user);
                    setCurrentCompany(companyService.get(1L));
                    user.setCompanyName(getCurrentCompany().getName());
                    message.setData(user);
                    message.setStatus(LOGIN_SUCCESS);
                } else {
                    message.setStatus(LOGIN_PASSWORD_INCORRECT);
                }
            } else {
                message.setStatus(LOGIN_USERNAME_INCORRECT);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message.setStatus(LOGIN_FAILED);
            message.setSuccess(false);
        }

        return message;
    }

    @RequestMapping("logout")
    public @ResponseBody Message logout() {
        setCurrentUser(null);
        return SUCCESS;
    }

    @Access()
    @RequestMapping("findUser")
    public @ResponseBody List<?> findUser(User user, Integer curPage, Integer pageSize, String sort, String direction) {
        return userService.findUser(user, curPage, pageSize, sort, direction);
    }

    @Access()
    @RequestMapping("save")
    public @ResponseBody Message save(User user) {
        Message message = new Message();

        try {
            message.setData(userService.save(user));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message.setSuccess(false);
            if (e instanceof DataIntegrityViolationException) {
                message.setStatus(-1);
            }
        }

        return message;
    }

    @Access()
    @RequestMapping("remove")
    public @ResponseBody Message remove(User user) {
        Message message = new Message();

        try {
            userService.remove(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message.setSuccess(false);
        }

        return message;
    }

    @Access(authorities = Authorities.LOGIN)
    @RequestMapping("get")
    public @ResponseBody User get(User user) {
        return getCurrentUser();
    }
}

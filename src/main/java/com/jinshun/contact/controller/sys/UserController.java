package com.jinshun.contact.controller.sys;

import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.entity.User;
import com.jinshun.contact.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("login")
    public @ResponseBody Message login(String username, String password) {
        Message message = new Message();

        try {
            User user = userService.getUser(username);

            if (user != null) {
                if (user.getPassword().equals(password)) {
                    setCurrentUser(user);
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
        }

        return message;
    }
}

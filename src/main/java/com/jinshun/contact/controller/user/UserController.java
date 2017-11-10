package com.jinshun.contact.controller.user;

import com.jinshun.contact.controller.common.ControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserController extends ControllerSupport {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    public Message login() {
        Message message  = new Message();

        try {

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return message;
    }
}

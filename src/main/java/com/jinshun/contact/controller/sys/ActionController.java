package com.jinshun.contact.controller.sys;

import com.jinshun.contact.controller.common.ControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/action")
public class ActionController extends ControllerSupport {

    private Logger logger = LoggerFactory.getLogger(ActionController.class);
}

package com.jinshun.contact;

import com.jinshun.contact.dao.UserRepository;
import com.jinshun.contact.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("first")
    public void first(Model model) {
        model.addAttribute("hello", "world");
        System.out.print(2222);
    }

    @RequestMapping("getUserById")
    @ResponseBody
    public User getUserById(Long id) {
        User u = userRepository.findOne(id);
        return u;
    }

}

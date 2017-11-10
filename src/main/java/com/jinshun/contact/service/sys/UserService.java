package com.jinshun.contact.service.sys;

import com.jinshun.contact.dao.sys.UserRepository;
import com.jinshun.contact.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String username) {
        return userRepository.getUser(username);
    }
}

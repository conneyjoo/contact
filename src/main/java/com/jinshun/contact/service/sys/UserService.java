package com.jinshun.contact.service.sys;

import com.jinshun.contact.dao.sys.UserRepository;
import com.jinshun.contact.entity.User;
import com.jinshun.contact.service.common.CommonService;
import com.jinshun.contact.util.SQLString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService extends CommonService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    public List<?> findUser(User user, Integer curPage, Integer pageSize, String sort, String direction) {
        SQLString sql = new SQLString("select u.*, r.name rolename  from t_user u left join t_role r on r.id = u.role_id where 1 = 1");
        sql.addParam("and u.username = ?", user.getUsername());
        sql.addParam("and u.name = ?", user.getName());
        sql.addParam("and u.phone = ?", user.getPhone());
        sql.append(!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(direction) ? "%s" : "order by id desc");

        return findPage(sql.toString(), curPage, pageSize, sort, direction, sql.getParams());
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void remove(User user) {
        executeUpdate("delete from t_role_menu where role_id = ?", user.getRole().getId());
        executeUpdate("delete from t_role_action where role_id = ?", user.getRole().getId());
        userRepository.delete(user);
    }
}

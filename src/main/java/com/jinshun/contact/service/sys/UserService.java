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
        sql.addCondition("and u.username = ?0", user.getUsername());
        sql.addCondition("and u.name = ?1", user.getName());
        sql.addCondition("and u.phone = ?2", user.getPhone());

        sort = StringUtils.isEmpty(sort) ? "id" : sort;
        direction = StringUtils.isEmpty(direction) ? "desc" : direction;

        return findPage(sql, curPage, pageSize, sort, direction);
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

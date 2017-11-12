package com.jinshun.contact.service.sys;

import com.jinshun.contact.dao.sys.UserRepository;
import com.jinshun.contact.entity.User;
import com.jinshun.contact.service.common.CommonService;
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
        StringBuffer sql = new StringBuffer("select u.*, r.name rolename  from t_user u left join t_role r on r.id = u.role_id where 1 = 1");

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(direction)) {
            sql.append(" %s");
        } else {
            sql.append(" order by id desc");
        }

        return findPage(sql.toString(), curPage, pageSize, sort, direction);
    }
}

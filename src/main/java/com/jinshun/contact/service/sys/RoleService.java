package com.jinshun.contact.service.sys;

import com.jinshun.contact.dao.sys.RoleRepository;
import com.jinshun.contact.dao.sys.UserRepository;
import com.jinshun.contact.entity.Role;
import com.jinshun.contact.entity.User;
import com.jinshun.contact.service.common.CommonService;
import com.jinshun.contact.util.SQLString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RoleService extends CommonService {

    @Autowired
    private RoleRepository roleRepository;

    public List<?> findRole(Role role) {
        SQLString sql = new SQLString("select r.* from t_role r where 1 = 1");
        sql.append("order by id desc");

        return find(sql);
    }
}

package com.jinshun.contact.service.sys;

import com.jinshun.contact.dao.sys.RoleRepository;
import com.jinshun.contact.dao.sys.UserRepository;
import com.jinshun.contact.entity.Menu;
import com.jinshun.contact.entity.Role;
import com.jinshun.contact.entity.User;
import com.jinshun.contact.service.common.CommonService;
import com.jinshun.contact.util.SQLString;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleService extends CommonService {

    @Autowired
    private RoleRepository roleRepository;

    public List<?> findRole(Role role) {
        SQLString sql = new SQLString("select r.* from t_role r where 1 = 1");
        sql.append("order by id desc");

        return find(sql);
    }

    public Role save(Role role, String[] menuIds, String[] actionIds) {
        role = roleRepository.save(role);

        if (menuIds != null && menuIds.length > 0) {
            List<String> list = new ArrayList<String>();
            for (String menuId : menuIds) {
                list.add(String.format("(%s, %s)", role.getId(), menuId));
            }
            executeUpdate("delete from t_role_menu where role_id = ?", role.getId());
            executeUpdate("insert into t_role_menu (role_id, menu_id) values " + StringUtils.join(list, ","));
        }

        if (actionIds != null && actionIds.length > 0) {
            List<String> list = new ArrayList<String>();
            for (String actionId : actionIds) {
                list.add(String.format("(%s, %s)", role.getId(), actionId));
            }
            executeUpdate("delete from t_role_action where role_id = ?", role.getId());
            executeUpdate("insert into t_role_action (role_id, action_id) values " + StringUtils.join(list, ","));
        }

        return role;
    }

    public void remove(Role role) {
        executeUpdate("delete from t_role_menu where role_id = ?", role.getId());
        executeUpdate("delete from t_role_action where role_id = ?", role.getId());
        roleRepository.delete(role.getId());
    }

    public List<?> getMenus(Long id) {
        String sql = "select m.*, rm.menu_id from t_menu m left join t_role_menu rm on rm.role_id = ? and rm.menu_id = m.id order by m.id desc";
        return find(sql, id);
    }

    public List<?> getActions(Long id) {
        String sql = "select a.*, ra.action_id from t_action a left join t_role_action ra on ra.role_id = ? and ra.action_id = a.id order by a.id desc";
        return find(sql, id);
    }
}

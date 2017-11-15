package com.jinshun.contact.service.sys;

import com.jinshun.contact.dao.sys.MenuRepository;
import com.jinshun.contact.entity.Menu;
import com.jinshun.contact.service.common.CommonService;
import com.jinshun.contact.util.SQLString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MenuService extends CommonService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getMenus(Long roleId) {
        return menuRepository.getMenus(roleId);
    }

    public List<?> findMenu(Menu menu) {
        SQLString sql = new SQLString("select m.* from t_menu m where 1 = 1");
        sql.append("order by id desc");

        return find(sql);
    }
}

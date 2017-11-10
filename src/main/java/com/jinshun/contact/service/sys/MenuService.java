package com.jinshun.contact.service.sys;

import com.jinshun.contact.dao.sys.MenuRepository;
import com.jinshun.contact.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getMenus(Long roleId) {
        return menuRepository.getMenus(roleId);
    }
}

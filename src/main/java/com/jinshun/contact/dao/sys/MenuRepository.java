package com.jinshun.contact.dao.sys;

import com.jinshun.contact.entity.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends CrudRepository<Menu, Long> {

    @Query(value = "select m.* from t_menu m, t_role_menu rm where rm.menu_id = m.id and rm.role_id = :roleId", nativeQuery = true)
    public List<Menu> getMenus(@Param("roleId") Long roleId);
}

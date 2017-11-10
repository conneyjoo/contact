package com.jinshun.contact.dao.sys;

import com.jinshun.contact.entity.Menu;
import com.jinshun.contact.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "t_menu")
@Qualifier("userRepository")
public interface MenuRepository extends CrudRepository<Menu, Long> {

    @Query(value = "select m.* from t_menu m, t_role_menu rm where rm.menu_id = m.id and rm.role_id = :roleId", nativeQuery = true)
    public List<Menu> getMenus(@Param("roleId") Long roleId);
}

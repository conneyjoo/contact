package com.jinshun.contact.dao.sys;

import com.jinshun.contact.entity.Menu;
import com.jinshun.contact.entity.Role;
import com.jinshun.contact.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "t_role")
@Qualifier("userRepository")
public interface RoleRepository extends CrudRepository<Role, Long>, JpaSpecificationExecutor<Role> {

}

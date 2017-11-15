package com.jinshun.contact.dao.sys;

import com.jinshun.contact.entity.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>, JpaSpecificationExecutor<Role> {

}

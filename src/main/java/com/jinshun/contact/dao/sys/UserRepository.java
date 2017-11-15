package com.jinshun.contact.dao.sys;

import com.jinshun.contact.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query(value = "select t from #{#entityName} t where username = :username")
    public User getUser(@Param("username") String username);

    @Query(value = "select u.*, r.name rolename  from t_user u left join t_role r on r.id = u.role_id where 1 = 1", nativeQuery = true)
    public List<User> findUser(User user);
}

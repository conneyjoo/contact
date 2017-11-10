package com.jinshun.contact.dao.sys;

import com.jinshun.contact.entity.User;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "t_user")
@Qualifier("userRepository")
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query(value = "select t from #{#entityName} t where username = :username")
    public User getUser(@Param("username") String username);
}

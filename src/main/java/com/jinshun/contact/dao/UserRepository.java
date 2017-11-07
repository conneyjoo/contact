package com.jinshun.contact.dao;

import com.jinshun.contact.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name="t_user")
@Qualifier("userRepository")
public interface UserRepository extends CrudRepository<User,Long> {

    public User findOne(Long id);

    public User save(User u);


}

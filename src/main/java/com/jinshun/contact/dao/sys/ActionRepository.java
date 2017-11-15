package com.jinshun.contact.dao.sys;

import com.jinshun.contact.entity.Action;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ActionRepository extends CrudRepository<Action, Long> {

    @Query(value = "select a.* from t_action a, t_role_action ra where ra.action_id = a.id and ra.role_id = :roleId", nativeQuery = true)
    public List<Action> getActions(@Param("roleId") Long roleId);

    @Query(value = "select a.method from t_action a, t_role_action ra where ra.action_id = a.id and ra.role_id = :roleId", nativeQuery = true)
    public Set<String> getActionSet(@Param("roleId") Long roleId);
}
package com.jinshun.contact.dao;

import com.jinshun.contact.entity.Credit;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CreditRepository extends CrudRepository<Credit, Long>, JpaSpecificationExecutor<Credit> {
}

package com.jinshun.contact.dao;

import com.jinshun.contact.entity.SuccessBidMoney;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SuccessBidMoneyRepository extends CrudRepository<SuccessBidMoney, Long>, JpaSpecificationExecutor<SuccessBidMoney> {
}

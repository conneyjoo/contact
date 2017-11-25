package com.jinshun.contact.dao;

import com.jinshun.contact.entity.SuccessBid;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SuccessBidRepository extends CrudRepository<SuccessBid, Long>, JpaSpecificationExecutor<SuccessBid> {
}

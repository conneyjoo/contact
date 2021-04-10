package com.jinshun.contact.dao;

import com.jinshun.contact.entity.License;
import com.jinshun.contact.entity.Seal;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SealRepository  extends CrudRepository<Seal, Long>, JpaSpecificationExecutor<Seal> {
}

package com.jinshun.contact.dao;

import com.jinshun.contact.entity.License;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface LicenseRepository extends CrudRepository<License, Long>, JpaSpecificationExecutor<License> {
}

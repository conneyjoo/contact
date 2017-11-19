package com.jinshun.contact.dao;

import com.jinshun.contact.entity.File;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File, Long>,JpaSpecificationExecutor<File> {
}

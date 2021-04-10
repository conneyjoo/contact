package com.jinshun.contact.dao;

import com.jinshun.contact.entity.Document;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long>, JpaSpecificationExecutor<Document> {
}

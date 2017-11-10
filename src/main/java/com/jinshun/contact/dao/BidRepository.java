package com.jinshun.contact.dao;

import com.jinshun.contact.entity.Bid;
import com.jinshun.contact.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BidRepository extends CrudRepository<Bid, Long>,JpaSpecificationExecutor<Bid> {




}

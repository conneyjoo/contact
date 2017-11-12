package com.jinshun.contact.service;

import com.jinshun.contact.dao.BidRepository;
import com.jinshun.contact.entity.Bid;
import com.jinshun.contact.util.ConditionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class BidService {

    public static final Logger LOGGER = LoggerFactory.getLogger(BidService.class);

    @Autowired
    private BidRepository bidRepository;

    public Bid saveOrUpdate(Bid bid){
        return bidRepository.save(bid);
    }

    public void delete(Long id){
        bidRepository.delete(id);
    }

    public Bid getById(Long id){
        return bidRepository.findOne(id);
    }


    public List<Bid> queryBids(Long companyId , Bid bid){
        if(companyId == null){
            LOGGER.info("companyId为空！");
            return null;
        }

        List<Bid> bids = bidRepository.findAll(new Specification<Bid>() {
            @Override
            public Predicate toPredicate(Root<Bid> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                List<Predicate> list = new ArrayList<Predicate>();

                if(ConditionUtils.checkNullOrBlank(bid.getArea())){
                    list.add(cb.equal(root.get("area").as(String.class),bid.getArea()));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        });
         return bids;
    }


}

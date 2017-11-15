package com.jinshun.contact.service;

import com.jinshun.contact.dao.BidRepository;
import com.jinshun.contact.entity.Bid;
import com.jinshun.contact.service.common.CommonService;
import com.jinshun.contact.util.ConditionUtils;
import com.jinshun.contact.util.SQLString;
import com.jinshun.contact.utils.model.BidQueryModel;
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
public class BidService extends CommonService {

    public static final Logger LOGGER = LoggerFactory.getLogger(BidService.class);

    @Autowired
    private BidRepository bidRepository;

    public Bid saveOrUpdate(Bid bid) {
        return bidRepository.save(bid);
    }

    public void delete(Long id) {
        bidRepository.delete(id);
    }

    public Bid getById(Long id) {
        return bidRepository.findOne(id);
    }

    public List<?> queryBids(BidQueryModel model, Integer curPage, Integer pageSize, String sort, String direction) {

        SQLString sql = new SQLString("select t.* from t_bid t where 1 = 1");
        sql.addCondition("and t.company_id = ?0", model.getCompanyId());
        if(ConditionUtils.checkNullOrBlank(model.getName())){
            sql.append(" and t.name like '%"+model.getName()+"%'");
        }
        sql.addCondition("and t.apply_date >= ?2", model.getApply_date_min());
        sql.addCondition("and t.apply_date <= ?3", model.getApply_date_max());
        sql.addCondition("and t.deposit_deadline >= ?4", model.getDeposit_deadline_min());
        sql.addCondition("and t.deposit_deadline <= ?5", model.getDeposit_deadline_max());

        sql.addCondition("and t.bid_open_time >= ?6", model.getBid_open_time_min());
        sql.addCondition("and t.bid_open_time <= ?7", model.getBid_open_time_max());

        sort = StringUtils.isEmpty(sort) ? "id" : sort;
        direction = StringUtils.isEmpty(direction) ? "desc" : direction;

        return findPage(sql, curPage, pageSize, sort, direction);
    }


}

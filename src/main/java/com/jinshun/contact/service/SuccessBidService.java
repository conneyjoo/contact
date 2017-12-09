package com.jinshun.contact.service;

import com.jinshun.contact.dao.SuccessBidRepository;
import com.jinshun.contact.entity.SuccessBid;
import com.jinshun.contact.entity.User;
import com.jinshun.contact.service.common.CommonService;
import com.jinshun.contact.util.ConditionUtils;
import com.jinshun.contact.util.SQLString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SuccessBidService extends CommonService {

    public static final Logger LOGGER = LoggerFactory.getLogger(SuccessBidService.class);

    @Autowired
    private SuccessBidRepository successBidRepository;

    public List<?> findSuccessBid(SuccessBid successBid, Integer curPage, Integer pageSize, String sort, String direction) {
        SQLString sql = new SQLString("select t.* from t_success_bid t where 1 = 1");

        sql.addCondition("and t.company_id = ?", successBid.getCompany().getId());
        sql.addCondition("and t.name like ?", StringUtils.isEmpty(successBid.getName()) ? null : "%" + successBid.getName() + "%");
        sql.addCondition("and t.area like ?", StringUtils.isEmpty(successBid.getArea()) ? null : "%" + successBid.getArea() + "%");
        sql.addCondition("and t.conclude_time >= ?", successBid.getConcludeTimeBegin());
        sql.addCondition("and t.conclude_time <= ?", successBid.getConcludeTimeEnd());

        if (successBid.getContactPriceRange() != null) {
            if (successBid.getContactPriceRange() == 1) {
                sql.addCondition("and t.contact_price <= ?", 5000000);
            } else if (successBid.getContactPriceRange() == 2) {
                sql.addCondition("and t.contact_price >= ?", 5000000);
                sql.addCondition("and t.contact_price <= ?", 10000000);
            } else if (successBid.getContactPriceRange() == 3) {
                sql.addCondition("and t.contact_price >= ?", 10000000);
            }
        }

        sql.addCondition("and t.in_warehouse = ?", successBid.getInWarehouse());

        sort = StringUtils.isEmpty(sort) ? "id" : sort;
        direction = StringUtils.isEmpty(direction) ? "desc" : direction;

        return findPage(sql, curPage, pageSize, sort, direction);
    }

    public SuccessBid save(SuccessBid successBid) {
        return successBidRepository.save(successBid);
    }

    public void remove(SuccessBid successBid) {
        executeUpdate("delete from t_success_bid_money where success_bid_id = ?", successBid.getId());
        successBidRepository.delete(successBid);
    }

    public void updateInWarehouse(SuccessBid successBid) {
        executeUpdate("update t_success_bid set in_warehouse = ? where id = ?", successBid.getInWarehouse(), successBid.getId());
    }
}

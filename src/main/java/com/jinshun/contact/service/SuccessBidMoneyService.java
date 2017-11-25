package com.jinshun.contact.service;

import com.jinshun.contact.dao.SuccessBidMoneyRepository;
import com.jinshun.contact.dao.SuccessBidRepository;
import com.jinshun.contact.entity.SuccessBid;
import com.jinshun.contact.entity.SuccessBidMoney;
import com.jinshun.contact.service.common.CommonService;
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
public class SuccessBidMoneyService extends CommonService {

    public static final Logger LOGGER = LoggerFactory.getLogger(SuccessBidMoneyService.class);

    @Autowired
    private SuccessBidMoneyRepository successBidMoneyRepository;

    public List<?> findSuccessBidMoney(SuccessBidMoney successBidMoney) {
        SQLString sql = new SQLString("select t.* from t_success_bid_money t where 1 = 1");
        sql.append("and t.success_bid_id = ?", successBidMoney.getSuccessBid().getId());
        sql.append("order by id desc");

        return find(sql);
    }

    public SuccessBidMoney save(SuccessBidMoney successBidMoney) {
        return successBidMoneyRepository.save(successBidMoney);
    }

    public void remove(SuccessBidMoney successBidMoney) {
        successBidMoneyRepository.delete(successBidMoney);
    }
}

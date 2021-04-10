package com.jinshun.contact.service;

import com.jinshun.contact.dao.CreditRepository;
import com.jinshun.contact.entity.Credit;
import com.jinshun.contact.service.common.CommonService;
import com.jinshun.contact.util.ConditionUtils;
import com.jinshun.contact.util.SQLString;
import com.jinshun.contact.utils.model.CreditQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CreditService extends CommonService {

    @Autowired
    private CreditRepository creditRepository;

    public Credit saveOrUpdate(Credit credit) {
        return creditRepository.save(credit);
    }

    public void delete(Long id) {
        creditRepository.delete(id);
    }

    public Credit getById(Long id) {
        return creditRepository.findOne(id);
    }

    public List<?> queryCredit(CreditQueryModel model, Integer curPage, Integer pageSize, String sort, String direction) {
        SQLString sql = new SQLString("select t.* from t_credit t where 1 = 1");
        sql.addCondition("and t.company_id = ?", model.getCompanyId());
        if (ConditionUtils.checkNullOrBlank(model.getName())) {
            sql.append(" and t.name like '%" + model.getName() + "%'");
        }
        if (ConditionUtils.checkNullOrBlank(model.getLevel())) {
            sql.append(" and t.level like '%" + model.getLevel() + "%'");
        }
        sort = StringUtils.isEmpty(sort) ? "id" : sort;
        direction = StringUtils.isEmpty(direction) ? "desc" : direction;

        return findPage(sql, curPage, pageSize, sort, direction);
    }



}

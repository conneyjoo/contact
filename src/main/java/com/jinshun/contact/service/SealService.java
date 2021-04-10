package com.jinshun.contact.service;

import com.jinshun.contact.dao.SealRepository;
import com.jinshun.contact.entity.License;
import com.jinshun.contact.entity.Seal;
import com.jinshun.contact.service.common.CommonService;
import com.jinshun.contact.util.ConditionUtils;
import com.jinshun.contact.util.SQLString;
import com.jinshun.contact.utils.model.LicenseQueryModel;
import com.jinshun.contact.utils.model.SealQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SealService extends CommonService {

    @Autowired
    private SealRepository sealRepository;

    public Seal saveOrUpdate(Seal seal) {
        return sealRepository.save(seal);
    }

    public void delete(Long id) {
        sealRepository.delete(id);
    }

    public Seal getById(Long id) {
        return sealRepository.findOne(id);
    }

    public List<?> querySeals(SealQueryModel model, Integer curPage, Integer pageSize, String sort, String direction) {
        SQLString sql = new SQLString("select t.* from t_seal t where 1 = 1");
        sql.addCondition("and t.company_id = ?", model.getCompanyId());
        if (ConditionUtils.checkNullOrBlank(model.getName())) {
            sql.append(" and t.name like '%" + model.getName() + "%'");
        }
        if (ConditionUtils.checkNullOrBlank(model.getType())) {
            sql.append(" and t.type like '%" + model.getType() + "%'");
        }
        sort = StringUtils.isEmpty(sort) ? "id" : sort;
        direction = StringUtils.isEmpty(direction) ? "desc" : direction;

        return findPage(sql, curPage, pageSize, sort, direction);
    }


}

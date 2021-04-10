package com.jinshun.contact.service;

import com.jinshun.contact.dao.LicenseRepository;
import com.jinshun.contact.entity.Bid;
import com.jinshun.contact.entity.License;
import com.jinshun.contact.service.common.CommonService;
import com.jinshun.contact.util.ConditionUtils;
import com.jinshun.contact.util.SQLString;
import com.jinshun.contact.utils.model.BidQueryModel;
import com.jinshun.contact.utils.model.LicenseQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LicenseService extends CommonService {

    @Autowired
    private LicenseRepository licenseRepository;

    public License saveOrUpdate(License license) {
        return licenseRepository.save(license);
    }

    public void delete(Long id) {
        licenseRepository.delete(id);
    }

    public License getById(Long id) {
        return licenseRepository.findOne(id);
    }

    public List<?> queryLicenses(LicenseQueryModel model, Integer curPage, Integer pageSize, String sort, String direction) {
        SQLString sql = new SQLString("select t.* from t_license t where 1 = 1");
        sql.addCondition("and t.company_id = ?", model.getCompanyId());
        if (ConditionUtils.checkNullOrBlank(model.getName())) {
            sql.append(" and t.name like '%" + model.getName() + "%'");
        }
        if (ConditionUtils.checkNullOrBlank(model.getType())) {
            sql.append(" and t.type like '%" + model.getType() + "%'");
        }
        if (ConditionUtils.checkNullOrBlank(model.getMajor())) {
            sql.append(" and t.major like '%" + model.getMajor() + "%'");
        }
        sort = StringUtils.isEmpty(sort) ? "id" : sort;
        direction = StringUtils.isEmpty(direction) ? "desc" : direction;

        return findPage(sql, curPage, pageSize, sort, direction);
    }


}

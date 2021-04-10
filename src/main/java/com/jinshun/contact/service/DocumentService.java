package com.jinshun.contact.service;

import com.jinshun.contact.dao.DocumentRepository;
import com.jinshun.contact.entity.Document;
import com.jinshun.contact.service.common.CommonService;
import com.jinshun.contact.util.ConditionUtils;
import com.jinshun.contact.util.SQLString;
import com.jinshun.contact.utils.model.DocumentQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DocumentService extends CommonService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document saveOrUpdate(Document document) {
        return documentRepository.save(document);
    }

    public void delete(Long id) {
        documentRepository.delete(id);
    }

    public Document getById(Long id) {
        return documentRepository.findOne(id);
    }

    public List<?> queryDocument(DocumentQueryModel model, Integer curPage, Integer pageSize, String sort, String direction) {
        SQLString sql = new SQLString("select t.* from t_document t where 1 = 1");
        sql.addCondition("and t.company_id = ?", model.getCompanyId());
        if (ConditionUtils.checkNullOrBlank(model.getName())) {
            sql.append(" and t.name like '%" + model.getName() + "%'");
        }
        if (ConditionUtils.checkNullOrBlank(model.getOffice())) {
            sql.append(" and t.office like '%" + model.getOffice() + "%'");
        }
        sort = StringUtils.isEmpty(sort) ? "id" : sort;
        direction = StringUtils.isEmpty(direction) ? "desc" : direction;

        return findPage(sql, curPage, pageSize, sort, direction);
    }

}

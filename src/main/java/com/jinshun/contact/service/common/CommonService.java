package com.jinshun.contact.service.common;

import com.jinshun.contact.util.SQLString;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CommonService {

    private static final int DEFAULT_PAGE_SIZE = 50;

    @Autowired
    @PersistenceContext
    private EntityManager em;

    public int executeUpdate(String sql, Object ...params) {
        Query query = em.createNativeQuery(sql);
        int i = 1;

        for (int len = params.length; i < len; i++) {
            query.setParameter(i, params[i]);
        }

        return query.executeUpdate();
    }

    public List<?> findPage(SQLString sql, Integer curPage, Integer pageSize, String sort, String direction) {
        return findPage(sql.toString(), curPage, pageSize, sort, direction, sql.getParams());
    }

   public List<?> findPage(String sql, Integer curPage, Integer pageSize, String sort, String direction, Object ...params) {
       if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(direction)) {
           sql += " order by " + sort + " " + direction;
       }

       Query query = em.createNativeQuery(sql);

       for (int i = 0, len = params.length; i < len; i++) {
           query.setParameter(i, params[i]);
       }

       curPage = curPage == null ? 0 : curPage;
       pageSize = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
       query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setFirstResult(curPage * pageSize).setMaxResults(pageSize);

       return convertEntityList(query.getResultList());
   }

    protected List convertEntityList(List list) {
        for (int i = 0, len = list.size(); i < len; i++) {
            list.set(i, convertEntityMap((Map<String, Object>) list.get(i)));
        }

        return list;
    }

    protected Map<String, Object> convertEntityMap(Map<String, Object> map) {
        Map<String, Object> entityMap = new HashMap<String, Object>();
        Iterator<String> iterator = map.keySet().iterator();
        String key = null;

        while (iterator.hasNext()) {
            key = iterator.next();
            entityMap.put(converPropertyName(key.toLowerCase()), map.get(key));
        }

        return entityMap;
    }

    protected String converPropertyName(String name) {
        String propertyName = "";
        int index = name.indexOf("_");

        if (index > -1) {
            String start = name.substring(0, index);
            String end = name.substring(index + 1);

            if (end.length() > 0) {
                return propertyName + start + converPropertyName(end.substring(0, 1).toUpperCase() + end.substring(1));
            } else {
                return propertyName + start;
            }
        } else {
            return name;
        }
    }
}

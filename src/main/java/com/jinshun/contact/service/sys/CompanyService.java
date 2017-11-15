package com.jinshun.contact.service.sys;

import com.jinshun.contact.dao.sys.CompanyRepository;
import com.jinshun.contact.entity.Company;
import com.jinshun.contact.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CompanyService extends CommonService {

    @Autowired
    private CompanyRepository companyRepository;

    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company get(Long id) {
        return companyRepository.findOne(id);
    }
}

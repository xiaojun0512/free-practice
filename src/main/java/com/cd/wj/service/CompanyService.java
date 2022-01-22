package com.cd.wj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cd.wj.entity.Company;

public interface CompanyService {

    Integer addCompany(Company company);

    Page<Company> getCompanyByCondition(Company company);

    Page<Company> getCompanyType(String id);
}

package com.cd.wj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cd.wj.mapper.CompanyDao;
import com.cd.wj.entity.Company;
import com.cd.wj.service.CompanyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyDao, Company> implements CompanyService {

    @Override
    public Integer addCompany(Company company) {
        return baseMapper.insert(company);
    }

    @Override
    public Page<Company> getCompanyByCondition(Company company) {
        Page<Company> page = new Page<>();
        if(company.getCurrent() != null && company.getPage() != null) {
            page.setCurrent(company.getCurrent());
            page.setPages(company.getPage());
        }
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(company.getName()),"name",company.getName())
                .orderByDesc("create_time");
        return baseMapper.selectPage(page,wrapper);
    }
}

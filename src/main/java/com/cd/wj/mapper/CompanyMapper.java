package com.cd.wj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cd.wj.entity.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyMapper extends BaseMapper<Company> {
    Page<Company> getCompanyType(Page<Company> page,@Param("id") Integer id);
}

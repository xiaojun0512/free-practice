package com.cd.wj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cd.wj.entity.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyDao extends BaseMapper<Company> {
}

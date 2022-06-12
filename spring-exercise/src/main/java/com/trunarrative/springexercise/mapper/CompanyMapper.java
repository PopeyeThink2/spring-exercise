package com.trunarrative.springexercise.mapper;

import com.trunarrative.springexercise.entity.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className: CompanyMapper
 * @author: wenjie.xia
 * @description: database operations in company table
 * @date: 11/06/2022 22:28
 * @version: 1.0
 */
@Mapper
@Repository
public interface CompanyMapper {

    List<Company> queryAll();

    List<Company> queryByCompanyNumber(String companyNumber);

    int add(Company company);
}

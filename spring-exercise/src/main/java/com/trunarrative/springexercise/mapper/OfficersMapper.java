package com.trunarrative.springexercise.mapper;

import com.trunarrative.springexercise.entity.Address;
import com.trunarrative.springexercise.entity.Officers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className: OfficersMapper
 * @author: wenjie.xia
 * @description: database operations in officers table
 * @date: 11/06/2022 23:16
 * @version: 1.0
 */
@Mapper
@Repository
public interface OfficersMapper {

    List<Officers> queryAll();

    List<Officers> queryByCompanyNumber(String companyNumber);

    int add(Officers officers);
}

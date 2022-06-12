package com.trunarrative.springexercise.mapper;

import com.trunarrative.springexercise.entity.Address;
import com.trunarrative.springexercise.entity.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className: AddressMapper
 * @author: wenjie.xia
 * @description: database operations in address table
 * @date: 11/06/2022 23:14
 * @version: 1.0
 */
@Mapper
@Repository
public interface AddressMapper {
    List<Address> queryAll();

    Address queryByCompanyNumber(String companyNumber);

    int add(Address address);
}

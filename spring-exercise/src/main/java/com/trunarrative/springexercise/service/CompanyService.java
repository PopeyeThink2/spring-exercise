package com.trunarrative.springexercise.service;

import com.trunarrative.springexercise.entity.Company;

import java.util.List;

/**
 * @className: CompanyService
 * @author: wenjie.xia
 * @date: 11/06/2022 13:44
 * @version: 1.0
 */
public interface CompanyService {

    List<Company> searchCompany(String companyNumber, String companyName, String onlyActive);

}

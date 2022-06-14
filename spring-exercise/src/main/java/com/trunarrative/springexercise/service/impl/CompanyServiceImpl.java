package com.trunarrative.springexercise.service.impl;

import com.google.gson.Gson;
import com.trunarrative.springexercise.entity.Address;
import com.trunarrative.springexercise.entity.Company;
import com.trunarrative.springexercise.entity.Officers;
import com.trunarrative.springexercise.entity.RequestParameter;
import com.trunarrative.springexercise.mapper.AddressMapper;
import com.trunarrative.springexercise.mapper.CompanyMapper;
import com.trunarrative.springexercise.mapper.OfficersMapper;
import com.trunarrative.springexercise.service.CompanyService;
import com.trunarrative.springexercise.service.OfficerService;
import com.trunarrative.springexercise.utils.WebHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: CompanyServiceImpl
 * @author: wenjie.xia
 * @description: company service implementation
 * @date: 11/06/2022 17:46
 * @version: 1.0
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private static final String COMPANY_SEARCH_URL
            = "https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Search?Query=";

    @Autowired
    OfficerService officerService;

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    OfficersMapper officersMapper;

    @Autowired
    WebHelper webHelper;

    /**
     * search company by search terms using TruNarrative API
     * @param companyNumber
     * @param companyName
     * @param onlyActive
     * @return company list
     */
    @Override
    public List<Company> searchCompany(String companyNumber, String companyName, String onlyActive) {
        RequestParameter requestParameter = webHelper.getParameter(companyNumber, companyName, onlyActive);
        if(requestParameter == null) {
            return new ArrayList<>();
        }
        // return the result from database if the endpoint is called with companyNumber.
        String number = requestParameter.getCompanyNumber();
        if(StringUtils.hasText(number)) {
            List<Company> companyList = getCompanyFromDatabase(number);
            if(companyList != null && !companyList.isEmpty()){
                return companyList;
            }
        }
        // concat search terms
        String searchTerm = "";
        if(StringUtils.hasText(companyNumber) && StringUtils.hasText(companyName)){
            searchTerm = String.join("&", companyNumber, companyName);
        } else {
            searchTerm = StringUtils.hasText(companyName) ? companyName : companyNumber;
        }
        // get response from api
        JSONArray jsonArray = webHelper.getJSONArray(COMPANY_SEARCH_URL + searchTerm);
        List<Company> companyList = new ArrayList<>();
        Gson gson = new Gson();
        for(int i = 0; i <jsonArray.length(); i++) {
            // convert json object to company class object
            Company company = gson.fromJson(String.valueOf(jsonArray.getJSONObject(i)), Company.class);
            // check whether the company is active. If not active and the 'onlyActive' parameter is added, skip it
            if(Boolean.TRUE.equals(requestParameter.getOnlyActive()) && !"active".equals(company.getCompany_status())){
                continue;
            }
            List<Officers> officers = officerService.searchOfficers(company.getCompany_number());
            insertOrUpdate(company, officers);
            // The officers of each company have to be included in the company details (new field officers)
            company.setOfficers(officers);
            companyList.add(company);
        }
        return companyList;
    }

    /**
     * query database by company number, add address and officers information to company object
     * @param number company number
     * @return company list
     */
    public List<Company> getCompanyFromDatabase(String number) {
        List<Company> companyList = companyMapper.queryByCompanyNumber(number);
        if(companyList != null && !companyList.isEmpty()) {
            for (Company company : companyList) {
                company.setAddress(addressMapper.queryByCompanyNumber(number));
                company.setOfficers(officersMapper.queryByCompanyNumber(number));
            }
        }
        return companyList;
    }

    /**
     * Save the companies (by company_number) and its officers and addresses in database
     * @param company
     * @param officers
     */
    public void insertOrUpdate(Company company, List<Officers> officers) {
        for(Officers officer : officers) {
            officersMapper.add(officer);
        }
        companyMapper.add(company);
        Address address = company.getAddress();
        address.setCompany_number(company.getCompany_number());
        addressMapper.add(address);
    }
}

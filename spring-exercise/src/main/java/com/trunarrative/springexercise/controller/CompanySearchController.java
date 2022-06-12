package com.trunarrative.springexercise.controller;

import com.google.gson.Gson;
import com.trunarrative.springexercise.entity.Company;
import com.trunarrative.springexercise.entity.Officers;
import com.trunarrative.springexercise.entity.RequestParameter;
import com.trunarrative.springexercise.service.CompanyService;
import com.trunarrative.springexercise.service.OfficerService;
import com.trunarrative.springexercise.utils.StringHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: CompanySearchController
 * @author: wenjie.xia
 * @description: Expose an endpoint that uses the TruProxyAPI to do a company and officer lookup via name or registration number.
 * @date: 11/06/2022 00:58
 * @version: 1.0
 */

@RestController
@RequestMapping("/TruProxyAPI")
public class CompanySearchController {

    @Autowired
    CompanyService companyService;

    @Autowired
    OfficerService officerService;

    @GetMapping("/v1/Search")
    public String getCompany(@RequestParam(value="companyNumber", required = false) String companyNumber,
                             @RequestParam(value="companyName", required = false) String companyName,
                             @RequestParam(value="onlyActive", required = false) String onlyActive) {
        //    A request parameter has to be added to decide whether only active companies should be returned
        //    Save the companies (by company_number) and its officers and addresses in a database and return the result from there if the endpoint is called with companyNumber.
        //    The officers of each company have to be included in the company details (new field officers)
        //    调用officers查询api，添加officer信息到company中
        List<Company> companyList = companyService.searchCompany(companyNumber, companyName, onlyActive);
        return new Gson().toJson(companyList);
    }

    @GetMapping("/v1/Officers/{number}")
    public String getOfficers(@PathVariable("number") String number) {
        List<Officers> officers  = officerService.searchOfficers(number);
        return new Gson().toJson(officers);
    }

}

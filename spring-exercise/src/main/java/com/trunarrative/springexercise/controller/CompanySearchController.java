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
import org.springframework.http.*;
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
    public ResponseEntity<List<Company>> getCompany(@RequestParam(value="companyNumber", required = false) String companyNumber,
                             @RequestParam(value="companyName", required = false) String companyName,
                             @RequestParam(value="onlyActive", required = false) String onlyActive) {
        List<Company> companyList = companyService.searchCompany(companyNumber, companyName, onlyActive);
        if(companyList == null || companyList.isEmpty()) {
            return new ResponseEntity<>(companyList, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    @GetMapping("/v1/Officers/{number}")
    public ResponseEntity<List<Officers>> getOfficers(@PathVariable("number") String number) {
        List<Officers> officers  = officerService.searchOfficers(number);
        if(officers == null || officers.isEmpty()) {
            return new ResponseEntity<>(officers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(officers, HttpStatus.OK);
    }

}

package com.trunarrative.springexercise.controller;

import com.trunarrative.springexercise.entity.Company;
import com.trunarrative.springexercise.entity.Officers;
import com.trunarrative.springexercise.entity.SearchResponse;
import com.trunarrative.springexercise.service.CompanyService;
import com.trunarrative.springexercise.service.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<SearchResponse> getCompany(@RequestParam(value="companyNumber", required = false) String companyNumber,
                             @RequestParam(value="companyName", required = false) String companyName,
                             @RequestParam(value="onlyActive", required = false) String onlyActive) {
        List<Company> companyList = companyService.searchCompany(companyNumber, companyName, onlyActive);
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setItems(companyList);
        if(companyList == null || companyList.isEmpty()) {
            searchResponse.setTotal_items(0);
            return new ResponseEntity<>(searchResponse, HttpStatus.NOT_FOUND);
        }
        searchResponse.setTotal_items(companyList.size());
        return new ResponseEntity<>(searchResponse, HttpStatus.OK);
    }

}

package com.trunarrative.springexercise.service.impl;

import com.google.gson.Gson;
import com.trunarrative.springexercise.entity.Officers;
import com.trunarrative.springexercise.mapper.OfficersMapper;
import com.trunarrative.springexercise.service.OfficerService;
import com.trunarrative.springexercise.utils.WebHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: OfficerServiceImpl
 * @author: wenjie.xia
 * @description: officers service implementation
 * @date: 11/06/2022 17:55
 * @version: 1.0
 */
@Service
public class OfficerServiceImpl implements OfficerService {

    private static final String OFFICERS_SEARCH_URL
            = "https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Officers?CompanyNumber=";

    @Autowired
    WebHelper webHelper;

    @Autowired
    OfficersMapper officersMapper;

    /**
     * search company officers by company number using TruNarrative API
     * @param number company number
     * @return officers list
     */
    @Override
    public List<Officers> searchOfficers(String number) {
        // query from database first
        List<Officers> officers = officersMapper.queryByCompanyNumber(number);
        if (officers == null || officers.isEmpty()) {
            officers = new ArrayList<>();
            JSONArray jsonArray = webHelper.getJSONArray(OFFICERS_SEARCH_URL + number);
            Gson gson = new Gson();
            for(int i = 0; i <jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Officers officer = gson.fromJson(String.valueOf(object), Officers.class);
                officer.setCompany_number(number);
                officers.add(officer);
            }
        }
        // Only include officers that are active (resigned_on is not present in that case)
        officers.removeIf(o -> o.getResigned_on() != null);
        return officers;
    }
}

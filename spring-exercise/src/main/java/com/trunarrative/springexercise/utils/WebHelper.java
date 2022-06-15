package com.trunarrative.springexercise.utils;

import com.trunarrative.springexercise.entity.RequestParameter;
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
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;


/**
 * @className: WebHelper
 * @author: wenjie.xia
 * @date: 11/06/2022 17:52
 * @version: 1.0
 */
@Component
public class WebHelper {

    private static final Logger logger = LoggerFactory.getLogger(WebHelper.class);

    @Value("${API_KEY}")
    private String API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * set request parameter from search terms
     * @param companyNumber
     * @param companyName
     * @param onlyActive
     * @return request parameters
     */
    public RequestParameter getParameter(String companyNumber, String companyName, String onlyActive) {
        if(!StringUtils.hasText(companyNumber) && !StringUtils.hasText(companyName) && !StringUtils.hasText(onlyActive)) {
            return null;
        }
        RequestParameter requestParameter = new RequestParameter();
        if (StringUtils.hasText(companyName)) {
            requestParameter.setCompanyName(companyName);
        }
        if (StringUtils.hasText(companyNumber)) {
            requestParameter.setCompanyNumber(companyNumber);
        }
        requestParameter.setOnlyActive("true".equals(onlyActive));
        return requestParameter;
    }

    /**
     * return JSON array of response entity from url
     * @param url
     * @return
     */
    public JSONArray getJSONArray(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        JSONArray jsonArray = null;
        try{
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(headers), String.class);
            JSONObject jsonObject = new JSONObject(response.getBody());
            jsonArray = jsonObject.getJSONArray("items");
        } catch (Exception e) {
            logger.warn(String.format("WebHelper.getJSONArray exception: %s, url: %s", e.getMessage(), url));
        }
        return jsonArray == null ? new JSONArray() : jsonArray;
    }
}

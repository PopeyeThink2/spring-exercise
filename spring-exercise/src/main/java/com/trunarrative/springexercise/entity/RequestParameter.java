package com.trunarrative.springexercise.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @className: RequestParameter
 * @author: wenjie.xia
 * @description: request parameter entity
 * @date: 11/06/2022 15:36
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestParameter {
    private String companyName;

    private String companyNumber;

    private Boolean onlyActive;

    @Override
    public String toString() {
        String searchTerm = "";
        if(StringUtils.hasText(this.companyNumber) && StringUtils.hasText(this.companyName)){
            searchTerm = String.join("&", this.companyNumber, this.companyName);
        } else {
            searchTerm = StringUtils.hasText(this.companyName) ? this.companyName : this.companyNumber;
        }
        return searchTerm;
    }
}

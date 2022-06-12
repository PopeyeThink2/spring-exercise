package com.trunarrative.springexercise.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

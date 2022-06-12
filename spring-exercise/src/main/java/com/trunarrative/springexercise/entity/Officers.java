package com.trunarrative.springexercise.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @className: Officers
 * @author: wenjie.xia
 * @description: officers entity
 * @date: 11/06/2022 01:07
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Officers {
    private String company_number;

    private String name;

    private String officer_role;

    private String appointed_on;

    private String resigned_on;

    private String data_of_birth;

    private String occupation;

    private String country_of_residence;

    private String nationality;

    private Address address;
}

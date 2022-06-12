package com.trunarrative.springexercise.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @className: Company
 * @author: wenjie.xia
 * @description: company entity
 * @date: 11/06/2022 01:04
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {

    private String company_number;

    private String company_type;

    private String title;

    private String company_status;

    private String date_of_creation;

    private String address_snippet;

    private String description;

    private String kind;

    private Address address;

    private List<Officers> officers;
}

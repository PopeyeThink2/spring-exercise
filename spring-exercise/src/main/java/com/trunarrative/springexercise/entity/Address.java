package com.trunarrative.springexercise.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: Address
 * @author: wenjie.xia
 * @description: address entity
 * @date: 11/06/2022 01:00
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

    private String company_number;

    private String locality;

    private String postal_code;

    private String premises;

    private String address_line_1;

    private String country;
}

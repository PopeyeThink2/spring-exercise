package com.trunarrative.springexercise.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @className: SearchResponse
 * @author: wenjie.xia
 * @description: store company search response
 * @date: 15/06/2022 09:37
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse {

    private List<Company> items;

    private int total_items;

}

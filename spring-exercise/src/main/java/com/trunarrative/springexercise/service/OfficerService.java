package com.trunarrative.springexercise.service;

import com.trunarrative.springexercise.entity.Officers;

import java.util.List;

/**
 * @className: OfficerService
 * @author: wenjie.xia
 * @date: 11/06/2022 14:15
 * @version: 1.0
 */
public interface OfficerService {

    List<Officers> searchOfficers(String number);
}

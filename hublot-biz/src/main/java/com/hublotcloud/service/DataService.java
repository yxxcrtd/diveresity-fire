package com.hublotcloud.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hublotcloud.domain.Data;
import com.hublotcloud.utils.JsonResult;

/**
 * DataService
 */
public interface DataService extends BaseService<Data, Long> {

    Page<Data> findAllWithPage(HttpServletRequest request, Pageable pageable, String keyword);

    JsonResult<Object> save(Data data);

}

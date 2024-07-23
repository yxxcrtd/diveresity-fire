package com.hublotcloud.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hublotcloud.domain.Atachment;
import com.hublotcloud.utils.JsonResult;

/**
 * AtachmentService
 */
public interface AtachmentService extends BaseService<Atachment, Long> {

    Page<Atachment> findAllWithPage(HttpServletRequest request, Pageable pageable, String keyword);

    JsonResult<Object> save(Atachment atachment);

}

package com.hublotcloud.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.hublotcloud.domain.Atachment;
import com.hublotcloud.repository.AtachmentRepository;
import com.hublotcloud.service.AtachmentService;
import com.hublotcloud.Constants;
import com.hublotcloud.utils.JsonResult;

/**
 * AtachmentServiceImpl
 */
@Service
public class AtachmentServiceImpl extends BaseServiceImpl<Atachment, Long> implements AtachmentService {

    @Resource
    private AtachmentRepository atachmentRepository;

    @Override
    public Page<Atachment> findAllWithPage(HttpServletRequest request, Pageable pageable, String keyword) {
        return atachmentRepository.findAllListWithPage(pageable, keyword);
    }

    @Override
    @Transactional
    public JsonResult<Object> save(Atachment atachment) {
        String returnString = Constants.SAVE_SUCCESS;
        if (ObjectUtils.isEmpty(atachment.getId())) {
            atachment.setId(Constants.LONG_0);
        }
        if (0 < atachment.getId()) {
            returnString = Constants.EDIT_SAVE;
        }
        return JsonResult.jsonResultSuccess(returnString, atachmentRepository.save(atachment));
    }

}

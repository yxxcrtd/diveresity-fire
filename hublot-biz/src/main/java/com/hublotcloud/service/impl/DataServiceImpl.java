package com.hublotcloud.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.hublotcloud.domain.Data;
import com.hublotcloud.repository.DataRepository;
import com.hublotcloud.service.DataService;
import com.hublotcloud.Constants;
import com.hublotcloud.utils.JsonResult;

/**
 * DataServiceImpl
 */
@Service
public class DataServiceImpl extends BaseServiceImpl<Data, Long> implements DataService {

    @Resource
    private DataRepository dataRepository;

    @Override
    public Page<Data> findAllWithPage(HttpServletRequest request, Pageable pageable, String keyword) {
        return dataRepository.findAllListWithPage(pageable, keyword);
    }

    @Override
    @Transactional
    public JsonResult<Object> save(Data data) {
        String returnString = Constants.SAVE_SUCCESS;
        if (ObjectUtils.isEmpty(data.getId())) {
            data.setId(Constants.LONG_0);
        }
        if (0 < data.getId()) {
            returnString = Constants.EDIT_SAVE;
        }
        dataRepository.save(data);
        return JsonResult.jsonResultSuccess(returnString, "");
    }

}

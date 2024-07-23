package com.hublotcloud.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hublotcloud.domain.Device;
import com.hublotcloud.repository.DeviceRepository;
import com.hublotcloud.service.DeviceService;
import com.hublotcloud.Constants;
import com.hublotcloud.utils.JsonResult;

/**
 * DeviceServiceImpl
 */
@Service
public class DeviceServiceImpl extends BaseServiceImpl<Device, Long> implements DeviceService {

    @Resource
    private DeviceRepository deviceRepository;

    @Override
    public Page<Device> findAllWithPage(HttpServletRequest request, Pageable pageable, String keyword) {
        return deviceRepository.findAllListWithPage(pageable, keyword);
    }

    @Override
    @Transactional
    public JsonResult<Object> save(Device device) {
        String returnString = Constants.SAVE_SUCCESS;
        if (0 < device.getId()) {
            returnString = Constants.EDIT_SAVE;
        }
        return JsonResult.jsonResultSuccess(returnString, deviceRepository.save(device));
    }

}

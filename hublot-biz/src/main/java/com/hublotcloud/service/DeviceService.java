package com.hublotcloud.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hublotcloud.domain.Device;
import com.hublotcloud.utils.JsonResult;

/**
 * DeviceService
 */
public interface DeviceService extends BaseService<Device, Long> {

    Page<Device> findAllWithPage(HttpServletRequest request, Pageable pageable, String keyword);

    JsonResult<Object> save(Device device);

}

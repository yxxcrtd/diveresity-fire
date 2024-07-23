package com.hublotcloud.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hublotcloud.domain.Equipment;
import com.hublotcloud.dto.EquipmentDto;
import com.hublotcloud.utils.JsonResult;

/**
 * EquipmentService
 */
public interface EquipmentService extends BaseService<Equipment, Long> {

    Page<EquipmentDto> findAllWithPage(HttpServletRequest request, Pageable pageable, String keyword);

    JsonResult<Object> save(Equipment equipment);

    Long getCount();

}

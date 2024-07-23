package com.hublotcloud.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.hublotcloud.Constants;
import com.hublotcloud.domain.Equipment;
import com.hublotcloud.dto.EquipmentDto;
import com.hublotcloud.repository.EquipmentRepository;
import com.hublotcloud.service.EquipmentService;
import com.hublotcloud.utils.JsonResult;

/**
 * EquipmentServiceImpl
 */
@Service
public class EquipmentServiceImpl extends BaseServiceImpl<Equipment, Long> implements EquipmentService {

    @Resource
    private EquipmentRepository equipmentRepository;

    @Override
    @Transactional
    public Page<EquipmentDto> findAllWithPage(HttpServletRequest request, Pageable pageable, String keyword) {
        return equipmentRepository.findAllListWithPage(pageable, keyword);
    }

    @Override
    @Transactional
    public JsonResult<Object> save(Equipment equipment) {
        String returnString = Constants.SAVE_SUCCESS;
        if (ObjectUtils.isEmpty(equipment.getId())) {
            equipment.setId(Constants.LONG_0);
        }
        if (0 < equipment.getId()) {
            returnString = Constants.EDIT_SAVE;
        }
        equipmentRepository.save(equipment);
        return JsonResult.jsonResultSuccess(returnString, "");
    }

    @Override
    public Long getCount() {
        return equipmentRepository.count();
    }

}

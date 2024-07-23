package com.hublotcloud.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hublotcloud.domain.Equipment;
import com.hublotcloud.service.EquipmentService;
import com.hublotcloud.utils.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Equipment Controller
 */
@Api(tags = "设备管理")
@RestController
@RequestMapping("biz/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    /**
     * List
     */
    @ApiOperation(value = "设备列表")
    @GetMapping("list")
    JsonResult<Object> list(HttpServletRequest request, @RequestParam(value = "p", defaultValue = "1") int p, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "k", defaultValue = "", required = false) String k) {
        Sort sort = Sort.by(new Sort.Order(Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(p - 1, size, sort);
        return JsonResult.jsonResultSuccess("获取设备列表", equipmentService.findAllWithPage(request, pageable, k));
    }

    /**
     * Edit
     */
    @ApiOperation(value = "设备详情")
    @GetMapping("edit")
    JsonResult<Object> edit(@RequestParam Long id) {
        Equipment equipment;
        if (0 == id) {
            equipment = new Equipment();
            equipment.setId(id);
        } else {
            equipment = equipmentService.getById(id);
        }
        return JsonResult.jsonResultSuccess("编辑或查看设备详情", equipment);
    }

    /**
     * Save
     */
    @ApiOperation(value = "设备保存")
    @PostMapping("save")
    JsonResult<Object> save(@RequestBody Equipment equipment) {
        return equipmentService.save(equipment);
    }

    /**
     * Count
     */
    @ApiOperation(value = "设备数量统计")
    @GetMapping("count")
    JsonResult<Object> count() {
        return JsonResult.jsonResultSuccess("获取设备数量统计", equipmentService.getCount());
    }

}

package com.hublotcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hublotcloud.service.AreaService;
import com.hublotcloud.utils.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Area Controller
 */
@Api(tags = "区划管理")
@RestController
@RequestMapping("biz/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
     * List
     */
    @ApiOperation(value = "区划列表")
    @GetMapping("list")
    JsonResult<Object> list(@RequestParam(value = "code", required = false) String code) {
        return JsonResult.jsonResultSuccess("获取区划列表", areaService.findByCode(code));
    }

}

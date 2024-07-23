package com.hublotcloud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hublotcloud.domain.Data;
import com.hublotcloud.service.DataService;
import com.hublotcloud.utils.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Data Controller
 */
@Api(tags = "监测因子数据管理")
@RestController
@RequestMapping("biz/data")
public class DataController {

    @Autowired
    private DataService dataService;

    /**
     * List
     */
    @ApiOperation(value = "数据列表")
    @GetMapping("list")
    JsonResult<Object> list(HttpServletRequest request, @RequestParam(value = "p", defaultValue = "1") int p, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "k", defaultValue = "", required = false) String k) {
        Sort sort = Sort.by(new Sort.Order(Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(p - 1, size, sort);
        return JsonResult.jsonResultSuccess("获取数据列表", dataService.findAllWithPage(request, pageable, k));
    }

    /**
     * Edit
     */
    @ApiOperation(value = "数据详情")
    @GetMapping("edit")
    JsonResult<Object> edit(@RequestParam Long id) {
        Data data;
        if (0 == id) {
            data = new Data();
            data.setId(id);
        } else {
            data = dataService.getById(id);
        }
        return JsonResult.jsonResultSuccess("编辑或查看数据详情", data);
    }

    /**
     * Save
     */
    @ApiOperation(value = "数据保存")
    @PostMapping("save")
    JsonResult<Object> save(@RequestBody Data data) {
        return dataService.save(data);
    }

}

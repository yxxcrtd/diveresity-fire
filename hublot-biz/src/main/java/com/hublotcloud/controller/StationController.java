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

import com.hublotcloud.domain.Station;
import com.hublotcloud.service.StationService;
import com.hublotcloud.utils.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Station Controller
 */
@Api(tags = "站点管理")
@RestController
@RequestMapping("biz/station")
public class StationController {

    @Autowired
    private StationService stationService;

    /**
     * List
     */
    @ApiOperation(value = "站点列表")
    @GetMapping("list")
    JsonResult<Object> list(HttpServletRequest request, @RequestParam(value = "p", defaultValue = "1") int p, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "k", defaultValue = "", required = false) String k) {
        Sort sort = Sort.by(new Sort.Order(Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(p - 1, size, sort);
        return JsonResult.jsonResultSuccess("获取站点列表", stationService.findAllWithPage(request, pageable, k));
    }

    /**
     * Edit
     */
    @ApiOperation(value = "站点详情")
    @GetMapping("edit")
    JsonResult<Object> edit(@RequestParam Long id) {
        Station station;
        if (0 == id) {
            station = new Station();
            station.setId(id);
        } else {
            station = stationService.getById(id);
        }
        return JsonResult.jsonResultSuccess("编辑或查看站点详情", station);
    }

    /**
     * Save
     */
    @ApiOperation(value = "站点保存")
    @PostMapping("save")
    JsonResult<Object> save(@RequestBody Station station) {
        return stationService.save(station);
    }

    /**
     * List
     */
    @ApiOperation(value = "所有站点列表")
    @GetMapping("all")
    JsonResult<Object> all() {
        Sort sort = Sort.by(new Sort.Order(Direction.DESC, "id"));
        return JsonResult.jsonResultSuccess("获取所有站点列表", stationService.findAll(sort));
    }

    /**
     * Count
     */
    @ApiOperation(value = "站点数量统计")
    @GetMapping("count")
    JsonResult<Object> count() {
        return JsonResult.jsonResultSuccess("获取站点数量统计", stationService.getCount());
    }

}

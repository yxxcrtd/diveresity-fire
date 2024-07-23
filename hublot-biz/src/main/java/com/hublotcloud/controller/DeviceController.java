package com.hublotcloud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hublotcloud.Constants;
import com.hublotcloud.domain.Device;
import com.hublotcloud.domain.User;
import com.hublotcloud.service.DeviceService;
import com.hublotcloud.utils.JsonResult;
import com.hublotcloud.utils.Pager;

/**
 * Device Controller
 */
@RestController
@RequestMapping("manage/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    /**
     * List
     */
    @GetMapping("")
    ModelAndView list(HttpServletRequest request, @RequestParam(value = "p", defaultValue = "1") int p, @RequestParam(value = "k", defaultValue = "", required = false) String k) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("k", k);
        Sort sort = Sort.by(new Sort.Order(Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(p - 1, Constants.PAGE_SIZE, sort);
        Page<Device> pages = deviceService.findAllWithPage(request, pageable, k);
        Pager pager = new Pager();
        pager.setPageSize(Constants.PAGE_SIZE);
        pager.setPageNo(p);
        long count = pages.getTotalElements();
        pager.setTotalCount(count);
        mav.addObject("count", count);
        mav.addObject("pager", pager);
        mav.addObject("list", pages.getContent());
        mav.addObject("active", "device");
        mav.setViewName("device/DeviceList");

        // 页面放入登录用户信息
        mav.addObject("user", (User) request.getSession().getAttribute(Constants.LOGIN_SESSION_KEY));
        return mav;
    }

    /**
     * Edit
     */
    @GetMapping("edit/{id}")
    ModelAndView edit(@PathVariable(value = "id") Long id) {
        ModelAndView mav = new ModelAndView();
        Device device;
        if (0 == id) {
            device = new Device();
            device.setId(id);
        } else {
            device = deviceService.getById(id);
        }
        mav.addObject("obj", device);
        mav.setViewName("device/DeviceEdit");
        return mav;
    }

    /**
     * Save
     */
    @PostMapping("save")
    JsonResult<Object> save(@ModelAttribute("device") @Valid Device device) {
        return JsonResult.jsonResultSuccess("保存成功!", deviceService.save(device));
    }

}

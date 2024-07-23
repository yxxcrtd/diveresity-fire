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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hublotcloud.domain.User;
import com.hublotcloud.service.UserService;
import com.hublotcloud.utils.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * User Controller
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("biz/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * List
     */
    @ApiOperation(value = "用户列表")
    @GetMapping("list")
    JsonResult<Object> list(HttpServletRequest request, @RequestParam(value = "p", defaultValue = "1") int p, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "k", defaultValue = "", required = false) String k) {
        Sort sort = Sort.by(new Sort.Order(Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(p - 1, size, sort);
        return JsonResult.jsonResultSuccess("获取用户列表", userService.findAllWithPage(request, pageable, k));
    }

    /**
     * Edit
     */
    @ApiOperation(value = "用户详情")
    @GetMapping("edit")
    JsonResult<Object> edit(@RequestParam Long id) {
        User user;
        if (0 == id) {
            user = new User();
            user.setId(id);
        } else {
            user = userService.getById(id);
        }
        return JsonResult.jsonResultSuccess("编辑或查看用户详情", user);
    }

    /**
     * Save
     */
    @ApiOperation(value = "用户保存")
    @PostMapping("save")
    JsonResult<Object> save(@ModelAttribute("data") @Valid User user) {
        return userService.save(user);
    }

    /**
     * List
     */
    @ApiOperation(value = "所有用户列表")
    @GetMapping("all")
    JsonResult<Object> all() {
        Sort sort = Sort.by(new Sort.Order(Direction.DESC, "id"));
        return JsonResult.jsonResultSuccess("获取所有用户列表", userService.findAll(sort));
    }

    /**
     * Count
     */
    @ApiOperation(value = "用户数量统计")
    @GetMapping("count")
    JsonResult<Object> count() {
        return JsonResult.jsonResultSuccess("获取用户数量统计", userService.getCount());
    }

}

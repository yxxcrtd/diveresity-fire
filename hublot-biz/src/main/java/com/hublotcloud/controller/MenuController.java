package com.hublotcloud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hublotcloud.Constants;
import com.hublotcloud.domain.Menu;
import com.hublotcloud.service.MenuService;
import com.hublotcloud.utils.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Menu Controller
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "getRouters")
    @GetMapping("getRouters")
    JsonResult<Object> getRouters(HttpServletRequest request) {
        List<Menu> menus = menuService.selectMenuTreeByUserId(1L);
        return JsonResult.jsonResultSuccess(Constants.OPERATE_SUCCESS, menuService.buildMenus(menus));
    }

}

package com.hublotcloud.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hublotcloud.Constants;
import com.hublotcloud.domain.User;
import com.hublotcloud.domain.UserInfo;
import com.hublotcloud.service.UserService;
import com.hublotcloud.utils.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Login Controller
 */
@Api(tags = "登录")
@RestController
@RequestMapping("")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Login
     */
    @PostMapping("login")
    JsonResult<Object> login(HttpServletRequest request, @RequestBody User user) {
        return userService.login(request, user.getUsername(), user.getPassword());
    }

    /**
     * getInfo
     * @throws Exception
     */
    @ApiOperation(value = "获取用户信息")
    @GetMapping("getInfo")
    JsonResult<Object> getUserInfo(HttpServletRequest request) throws Exception {
        User user = (User) request.getSession().getAttribute(Constants.LOGIN_SESSION_KEY);
        if (ObjectUtils.isEmpty(user)) {
            return JsonResult.jsonResultFail("没有用户对象");
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setPermissions("*:*:*");
        userInfo.setRoles("admin");
        user.setAvatar("");
        userInfo.setUser(user);

        return JsonResult.jsonResultSuccess("获取用户信息", userInfo);
    }

}

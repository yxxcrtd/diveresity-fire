package com.hublotcloud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hublotcloud.utils.JsonResult;

import io.swagger.annotations.Api;

/**
 * Logout Controller
 */
@Api(tags = "注销")
@RestController
@RequestMapping("")
public class LogoutController {

    @GetMapping("logout")
    JsonResult<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        // try {
        //     User user = (User) request.getSession().getAttribute(Constants.LOGIN_SESSION_KEY);
        //     if (ObjectUtils.isEmpty(user)) {
        //         return JsonResult.jsonResultFail("没有用户对象");
        //     }
        //     System.out.println(user.getUsername() + "注销");
        //     request.getSession().setAttribute(Constants.LOGIN_SESSION_KEY, null);
        //     return JsonResult.jsonResultSuccess("注销成功", "");
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // return JsonResult.jsonResultFail(Constants.OPERATE_FAIL);

        return JsonResult.jsonResultSuccess(null, null);
    }

}

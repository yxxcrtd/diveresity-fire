package com.hublotcloud.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hublotcloud.domain.User;
import com.hublotcloud.utils.JsonResult;

/**
 * UserService
 */
public interface UserService extends BaseService<User, Long> {

    JsonResult<Object> login(HttpServletRequest request, String username, String password);

    Page<User> findAllWithPage(HttpServletRequest request, Pageable pageable, String keyword);

    User findByUsername(String username);

    JsonResult<Object> login(String username, String password, String role);

    JsonResult<Object> save(User user);

    Long getCount();

}

package com.hublotcloud.service.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import com.adobe.internal.xmp.impl.Base64;
import com.hublotcloud.Constants;
import com.hublotcloud.domain.User;
import com.hublotcloud.repository.UserRepository;
import com.hublotcloud.service.UserService;
import com.hublotcloud.utils.JsonResult;
import com.hublotcloud.utils.StringUtil;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * login
     */
    @Override
    @Transactional
    public JsonResult<Object> login(HttpServletRequest request, String username, String password) {
        User user = userRepository.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            return JsonResult.jsonResultFail(Constants.LOGIN_USERNAME_OR_PASSWORD_WRONG);
        }
        String md5Password = StringUtil.md5String(password);
        if (!ObjectUtils.nullSafeEquals(md5Password, user.getPassword())) {
            logger.error(user.getUsername() + " 登录失败! " + Constants.LOGIN_PASSWORD_WRONG);
            return JsonResult.jsonResultFail(Constants.LOGIN_USERNAME_OR_PASSWORD_WRONG);
        }
        String token = UUID.randomUUID().toString().replaceAll("-", "") + UUID.randomUUID().toString().replaceAll("-", "");
        request.getSession().setAttribute(Constants.TOKEN, token);
        request.getSession().setAttribute(Constants.LOGIN_SESSION_KEY, user);
        return JsonResult.jsonResultSuccess(Constants.SUCCESS, token);
    }

    public static void main(String[] args) {
        System.out.println(StringUtil.md5String("123456"));
        System.out.println(StringUtil.md5String(Base64.decode("123456")));
    }

    // 后台用户管理
    @Override
    public Page<User> findAllWithPage(HttpServletRequest request, Pageable pageable, String keyword) {
        return userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            return null;
        }, pageable);
    }

    // 保存
    @Override
    @Transactional
    public JsonResult<Object> save(User user) {
        String returnString = Constants.SAVE_SUCCESS;
        if (ObjectUtils.isEmpty(user.getId())) {
            user.setId(Constants.LONG_0);
            user.setPassword(StringUtil.md5String(user.getPassword()));
        }
        if (0 < user.getId()) {
            returnString = Constants.EDIT_SAVE;
        }
        return JsonResult.jsonResultSuccess(returnString, userRepository.save(user));
    }

    // 保存用户信息
    // @Transactional
    // public JsonResult<Object> save(User user) {

    //     // 新增用户时，usernameUser 对象为空，不检查用户名重复
    //     User usernameUser = userRepository.findByUsername(user.getUsername());
    //     if (null != usernameUser) {
    //         // 修改用户时，如果id不相等，那就是2个不同的用户
    //         if (user.getId() != usernameUser.getId()) {
    //             return JsonResult.jsonResultFail(Constants.FAIL_USERNAME_EXIST);
    //         }
    //     }

    //     // 验证手机号码不能重复
    //     User phoneUser = userRepository.findByPhone(user.getPhone());
    //     if (null != phoneUser) {
    //         if (user.getId() != phoneUser.getId()) {
    //             return JsonResult.jsonResultFail(Constants.FAIL_PHONE_EXIST);
    //         }
    //     }

    //     return JsonResult.jsonResultSuccess(Constants.SUCCESS_SAVE, userRepository.save(user));
    // }

    // 登录时候查找用户
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 密码重置
    @Transactional
    public JsonResult<Object> resetPassword(HttpServletRequest request, Long uid) {
        User user = userRepository.getReferenceById(uid);
        if (null != user && Constants.INT_1 < user.getId()) {

            user.setPassword(DigestUtils.md5DigestAsHex((Constants.DEFAULT_PASSWORD + Constants.LOGIN_USERNAME_SALT).getBytes()));
            userRepository.save(user);

            return JsonResult.jsonResultSuccess(Constants.SUCCESS_USER_RESET_PASSWROD, null);
        }

        return JsonResult.jsonResultFail(Constants.FAIL);
    }

    @Override
    public JsonResult<Object> login(String username, String password, String role) {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public Long getCount() {
        return userRepository.count();
    }

}

// package com.hublotcloud.config;

// import static com.hublotcloud.utils.JsonResult.jsonResultFail;

// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// import com.hublotcloud.utils.JsonResult;

// /**
//  * 全局异常处理
//  * @author young
//  */
// @RestControllerAdvice
// public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    JsonResult<Object> defaultErrorHandler(Exception e) {
//        return jsonResultFail("出错了！请联系系统管理员！");
//    }

// }

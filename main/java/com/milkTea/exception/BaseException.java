package com.milkTea.exception;


import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.milkTea.util.AjaxResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.awt.print.PrinterException;

/**
 * 自定义异常解析
 */
@RestControllerAdvice
public class BaseException {

    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e){
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public AjaxResult handleException(RuntimeException e){
        return AjaxResult.error(e.getMessage());
    }

    // 没有token
    @ExceptionHandler(BindException.class)
    public AjaxResult handleBindException(BindException e){
        return AjaxResult.error(401,"资源认证失败！");
    }

    // token过期
    @ExceptionHandler(TokenExpiredException.class)
    public AjaxResult handleException(TokenExpiredException e){
        return AjaxResult.error(405,"身份已过期！");
    }

    // 传入无效token
    @ExceptionHandler(JWTDecodeException.class)
    public AjaxResult handleException(JWTDecodeException e){
        return AjaxResult.error(401,"资源认证失败!");
    }


}

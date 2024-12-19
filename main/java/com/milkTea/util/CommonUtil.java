package com.milkTea.util;


import com.github.pagehelper.PageHelper;
import com.milkTea.bean.Page;
import com.milkTea.bean.MUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工具类
 */
public class CommonUtil {

    private static final String TOKEN_KEY = "TOKEN";

//      生成订单编号
      public static String  getOrderNo(){
          return new SimpleDateFormat("YYYYMMDDHHmmssSSS").format(new Date());
      }


    /**
     * 密码加密
     * @return 加密密码
     */
    public static String encryptionPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 校验密码
     * @param password 密码
     * @param encryptionPassword 加密密码
     * @return 相等为true
     */
    public static boolean verifyPassword(String password, String encryptionPassword){
        if(password == null || encryptionPassword == null){
            throw new RuntimeException("密码不能为空！");
        }
        return encryptionPassword(password).equals(encryptionPassword);
    }


    /**
     * 获取request对象
     * @return request对象
     */
    public static HttpServletRequest getRequest(){
        return  ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取分页参数
     * @return 分页参数
     */
    public static Page getPage(){
        Page page = null;
        HttpServletRequest request = getRequest();
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        if(pageSize != null && pageNum != null){
            page = new Page();
            page.setPageSize(Integer.parseInt(pageSize));
            page.setPageNum(Integer.parseInt(pageNum));
        }
        return page;
    }

    /**
     * 开启分页
     */
    public static void startPage(){
        Page page = getPage();
        if(page != null)
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
    }

    public static MUser getUser(){
        String token =  getRequest().getHeader("token");
        return JWTUtils.getUser(token);
    }


}

package com.milkTea.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.milkTea.bean.MUser;
import com.milkTea.bean.body.LoginBody;
import com.milkTea.bean.body.RegisterBody;
import com.milkTea.service.IMUserService;
import com.milkTea.service.LoginService;
import com.milkTea.util.AjaxResult;
import com.milkTea.util.CommonUtil;
import com.milkTea.util.JWTUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {


    private final IMUserService userService;

    @Override
    public AjaxResult login(LoginBody loginBody) {
        if(StrUtil.isBlank(loginBody.getUserName())){
            return AjaxResult.error("用户名不能为空！");
        }
        if(StrUtil.isBlank(loginBody.getPassword())){
            return AjaxResult.error("密码不能为空！");
        }
        MUser user = userService.
                lambdaQuery().
                eq(MUser::getUserName, loginBody.getUserName()).
                one();
        if(ObjectUtil.isNull(user)){
            return AjaxResult.error("当前用户名未注册！");
        }
        if(!CommonUtil.verifyPassword(loginBody.getPassword(), user.getPassword())){
            return AjaxResult.error("密码错误，请重新输入！");
        }
        String token = JWTUtils.createToken(user);
        return AjaxResult.success().put("token", token);
    }

    @Override
    public AjaxResult register(RegisterBody registerBody) {
        if(StrUtil.isBlank(registerBody.getUserName())){
            return AjaxResult.error("用户名不能为空！");
        }
        if(StrUtil.isBlank(registerBody.getPassword())){
            return AjaxResult.error("密码不能为空！");
        }
        MUser user = userService.
                lambdaQuery().
                eq(MUser::getUserName, registerBody.getUserName()).
                one();
        if(ObjectUtil.isNotNull(user)){
            return AjaxResult.error(String.format("当前账号%s已被注册！", registerBody.getUserName()));
        }
        MUser mUser = new MUser();
        mUser.setPassword(CommonUtil.encryptionPassword(registerBody.getPassword()));
        mUser.setUserName(registerBody.getUserName());
        mUser.setRole(2);
        boolean save = userService.save(mUser);
        if(!save)
            return AjaxResult.error(String.format("当前账号%s注册失败！", registerBody.getUserName()));
        return AjaxResult.success("注册成功！");
    }
}

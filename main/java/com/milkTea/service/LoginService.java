package com.milkTea.service;

import com.milkTea.bean.body.LoginBody;
import com.milkTea.bean.body.RegisterBody;
import com.milkTea.util.AjaxResult;

public interface LoginService {

    AjaxResult login(LoginBody loginBody);

    AjaxResult register(RegisterBody registerBody);
}

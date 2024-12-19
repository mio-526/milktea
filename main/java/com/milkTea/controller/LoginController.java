package com.milkTea.controller;

import com.milkTea.bean.body.LoginBody;
import com.milkTea.bean.body.RegisterBody;
import com.milkTea.service.LoginService;
import com.milkTea.util.AjaxResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody){
        return loginService.login(loginBody);
    }

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody registerBody){
        return loginService.register(registerBody);
    }

    @PostMapping("/logout")
    public AjaxResult logout(){
        return AjaxResult.success();
    }

}

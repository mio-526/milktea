package com.milkTea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milkTea.bean.MUser;
import com.milkTea.service.IMUserService;
import com.milkTea.util.AjaxResult;
import com.milkTea.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class MUserController {


    private final IMUserService userService;

    @GetMapping("/list")
    public AjaxResult list(MUser user){
        CommonUtil.startPage();
        return AjaxResult.genTable(userService.list(new LambdaQueryWrapper<>(user)));
    }

    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id){
        return AjaxResult.success(userService.getById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody MUser user){
        user.setPassword(CommonUtil.encryptionPassword(user.getPassword()));
        return AjaxResult.success(userService.save(user));
    }

    @PutMapping
    public AjaxResult update(@RequestBody MUser user){
        return AjaxResult.success(userService.updateById(user));
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") Integer[] ids){
        return AjaxResult.success(userService.removeByIds(Arrays.asList(ids)));
    }
}

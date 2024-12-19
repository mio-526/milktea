package com.milkTea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milkTea.bean.MEmployee;
import com.milkTea.service.IMEmployeeService;
import com.milkTea.util.AjaxResult;
import com.milkTea.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class MEmployeeController {


    private final IMEmployeeService employeeService;

    @GetMapping("/list")
    public AjaxResult list(MEmployee employee){
        CommonUtil.startPage();
        return AjaxResult.genTable(employeeService.list(new LambdaQueryWrapper<>(employee)));
    }

    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id){
        return AjaxResult.success(employeeService.getById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody MEmployee employee){
        return AjaxResult.success(employeeService.save(employee));
    }

    @PutMapping
    public AjaxResult update(@RequestBody MEmployee employee){
        return AjaxResult.success(employeeService.updateById(employee));
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") Integer[] ids){
        return AjaxResult.success(employeeService.removeByIds(Arrays.asList(ids)));
    }
}

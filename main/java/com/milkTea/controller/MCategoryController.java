package com.milkTea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milkTea.bean.MCategory;
import com.milkTea.service.IMCategoryService;
import com.milkTea.util.AjaxResult;
import com.milkTea.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class MCategoryController {


    private final IMCategoryService categoryService;

    @GetMapping("/list")
    public AjaxResult list(MCategory category){
        CommonUtil.startPage();
        return AjaxResult.genTable(categoryService.list(new LambdaQueryWrapper<>(category)));
    }

    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id){
        return AjaxResult.success(categoryService.getById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody MCategory category){
        return AjaxResult.success(categoryService.save(category));
    }

    @PutMapping
    public AjaxResult update(@RequestBody MCategory category){
        return AjaxResult.success(categoryService.updateById(category));
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") Integer[] ids){
        return AjaxResult.success(categoryService.removeByIds(Arrays.asList(ids)));
    }
}

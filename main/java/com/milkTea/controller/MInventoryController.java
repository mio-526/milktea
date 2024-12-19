package com.milkTea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milkTea.bean.MInventory;
import com.milkTea.service.IMInventoryService;
import com.milkTea.util.AjaxResult;
import com.milkTea.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;


@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class MInventoryController {


    private final IMInventoryService inventoryService;

    @GetMapping("/list")
    public AjaxResult list(MInventory inventory){
        CommonUtil.startPage();
        return AjaxResult.genTable(inventoryService.list(new LambdaQueryWrapper<>(inventory)));
    }

    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id){
        return AjaxResult.success(inventoryService.getById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody MInventory inventory){
        inventory.setCreateTime(new Date());
        return AjaxResult.success(inventoryService.save(inventory));
    }

    @PutMapping
    public AjaxResult update(@RequestBody MInventory inventory){
        return AjaxResult.success(inventoryService.updateById(inventory));
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") Integer[] ids){
        return AjaxResult.success(inventoryService.removeByIds(Arrays.asList(ids)));
    }
}

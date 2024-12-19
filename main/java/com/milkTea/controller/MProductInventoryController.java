package com.milkTea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milkTea.bean.MProductInventory;
import com.milkTea.service.IMProductInventoryService;
import com.milkTea.util.AjaxResult;
import com.milkTea.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/productInventory")
@AllArgsConstructor
public class MProductInventoryController {


    private final IMProductInventoryService productInventoryService;

    @GetMapping("/list")
    public AjaxResult list(){
        CommonUtil.startPage();
        return AjaxResult.genTable(productInventoryService.list());
    }

    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id){
        return AjaxResult.success(productInventoryService.getById(id));
    }

    @PostMapping("/batch")
    public AjaxResult batch(@RequestBody List<MProductInventory> mProductInventory){
        if(mProductInventory == null || mProductInventory.isEmpty()){
            return AjaxResult.error("原料不能为空！");
        }
        productInventoryService.remove(new LambdaQueryWrapper<MProductInventory>().eq(MProductInventory::getProductId,mProductInventory.get(0).getProductId()));
        return AjaxResult.success(productInventoryService.saveBatch(mProductInventory));
    }

    @PostMapping
    public AjaxResult add(@RequestBody MProductInventory productInventory){
        return AjaxResult.success(productInventoryService.save(productInventory));
    }

    @PutMapping
    public AjaxResult update(@RequestBody MProductInventory productInventory){
        return AjaxResult.success(productInventoryService.updateById(productInventory));
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") Integer[] ids){
        return AjaxResult.success(productInventoryService.removeByIds(Arrays.asList(ids)));
    }
}

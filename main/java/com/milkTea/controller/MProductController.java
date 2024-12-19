package com.milkTea.controller;

import com.milkTea.bean.MProduct;
import com.milkTea.service.IMProductService;
import com.milkTea.util.AjaxResult;
import com.milkTea.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;


@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class MProductController {


    private final IMProductService productService;

    @GetMapping("/list")
    public AjaxResult list(MProduct product){
        CommonUtil.startPage();
        return AjaxResult.genTable(productService.selectMProductList(product));
    }

    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id){
        return AjaxResult.success(productService.getById(id));
    }

    @GetMapping("/productInventory/{id}")
    public AjaxResult productInventory(@PathVariable("id") Integer id){
        return AjaxResult.success(productService.selectProductInventoryByProductId(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody MProduct product){
        product.setCreateTime(new Date());
        return AjaxResult.success(productService.save(product));
    }

    @PutMapping
    public AjaxResult update(@RequestBody MProduct product){
        return AjaxResult.success(productService.updateById(product));
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") Integer[] ids){
        return AjaxResult.success(productService.removeByIds(Arrays.asList(ids)));
    }
}

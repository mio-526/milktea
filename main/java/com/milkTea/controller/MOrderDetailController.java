package com.milkTea.controller;

import com.milkTea.bean.MOrderDetail;
import com.milkTea.service.IMOrderDetailService;
import com.milkTea.util.AjaxResult;
import com.milkTea.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/api/orderDetail")
@AllArgsConstructor
public class MOrderDetailController {


    private final IMOrderDetailService orderDetailService;

    @GetMapping("/list")
    public AjaxResult list(MOrderDetail orderDetail){
        CommonUtil.startPage();
        return AjaxResult.genTable(orderDetailService.selectOrderDetailListView(orderDetail));
    }

    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id){
        return AjaxResult.success(orderDetailService.getById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody MOrderDetail orderDetail){
        return AjaxResult.success(orderDetailService.save(orderDetail));
    }

    @PutMapping
    public AjaxResult update(@RequestBody MOrderDetail orderDetail){
        return AjaxResult.success(orderDetailService.updateById(orderDetail));
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") Integer[] ids){
        return AjaxResult.success(orderDetailService.removeByIds(Arrays.asList(ids)));
    }
}

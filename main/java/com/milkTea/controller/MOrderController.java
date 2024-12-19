package com.milkTea.controller;

import com.milkTea.bean.MOrder;
import com.milkTea.service.IMOrderService;
import com.milkTea.util.AjaxResult;
import com.milkTea.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class MOrderController {


    private final IMOrderService orderService;

    @GetMapping("/list")
    public AjaxResult list(MOrder order){
        CommonUtil.startPage();
        return AjaxResult.genTable(orderService.selectOrderList(order));
    }

    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id){
        return AjaxResult.success(orderService.getById(id));
    }

    @PutMapping("/success")
    public AjaxResult success(@RequestBody MOrder order){
        MOrder mOrder = orderService.getById(order.getId());
        if(mOrder == null){
            return AjaxResult.error("该订单不存在！");
        }
        if(mOrder.getStatus() == 3){
            return AjaxResult.error("当前订单已取消！");
        }
        if(mOrder.getStatus() ==2){
            return AjaxResult.error("当前订单已完成！");
        }
        order.setStatus(2);
        return AjaxResult.success(orderService.updateById(order));
    }

    @PostMapping
    public AjaxResult add(@RequestBody MOrder order){
        return AjaxResult.success(orderService.save(order));
    }

    @PutMapping
    public AjaxResult update(@RequestBody MOrder order){
        return AjaxResult.success(orderService.updateById(order));
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") Integer[] ids){
        return AjaxResult.success(orderService.removeByIds(Arrays.asList(ids)));
    }
}

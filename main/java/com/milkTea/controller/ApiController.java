package com.milkTea.controller;

import com.milkTea.bean.MOrder;
import com.milkTea.bean.MOrderDetail;
import com.milkTea.bean.MUser;
import com.milkTea.service.ApiService;
import com.milkTea.util.AjaxResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("/getInfo")
    public AjaxResult getInfo() {
        return apiService.getInfo();
    }

    @GetMapping("/orderList")
    public AjaxResult orderList(MOrder order) {
        return apiService.orderList(order);
    }

    @GetMapping("/orderByOrderNo/{orderNo}")
    public AjaxResult getOrderById(@PathVariable("orderNo") String orderNo) {
        return apiService.getOrderByOrderNo(orderNo);
    }

    @PostMapping("/createOrder")
    public AjaxResult createOrder(@RequestBody List<MOrderDetail> orderDetailList) {
        return apiService.createOrder(orderDetailList);
    }

    @PutMapping("/cancelOrder")
    public AjaxResult cancelOrder(@RequestBody MOrder order) {
        return apiService.cancelOrder(order);
    }

    /**
     * 今日销售数量
     */
    @GetMapping("/daySalesNum")
    public AjaxResult getDaySalesNum(){
        return apiService.getProductNum();
    }

    /**
     * 获取近五日销售数量
     */
    @GetMapping("/fiveDaysOrderNum")
    public AjaxResult getFiveDaysOrderNum(){
        return apiService.getFiveDaysOrderNum();
    }

    @GetMapping("/inventoryConsumptionNum")
    public AjaxResult getInventoryConsumptionNum(){
        return apiService.getInventoryConsumptionNum();
    }

    @PutMapping("/restPassword")
    public AjaxResult restPassword(@RequestBody MUser user) {
        return apiService.restPassword(user);
    }
}

package com.milkTea.service;

import com.milkTea.bean.MOrder;
import com.milkTea.bean.MOrderDetail;
import com.milkTea.bean.MUser;
import com.milkTea.util.AjaxResult;

import java.util.List;

public interface ApiService {

    AjaxResult getInfo();

    AjaxResult createOrder(List<MOrderDetail> orderDetailList);

    AjaxResult cancelOrder(MOrder order);

    AjaxResult orderList(MOrder order);

    AjaxResult getOrderByOrderNo(String orderNo);

    AjaxResult getProductNum();

    AjaxResult getFiveDaysOrderNum();

    AjaxResult getInventoryConsumptionNum();

    AjaxResult restPassword(MUser user);
}

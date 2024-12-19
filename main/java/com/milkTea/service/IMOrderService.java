package com.milkTea.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.milkTea.bean.MOrder;
import com.milkTea.bean.view.CommonView;
import com.milkTea.bean.view.MOrderView;

import java.util.List;

public interface IMOrderService extends IService<MOrder> {

    List<MOrderView> selectOrderListView(MOrder order);

    MOrderView selectOrderViewByOrderNo(String orderNo);

    List<MOrderView> selectOrderList(MOrder order);

    CommonView selectOrderNum(MOrder order);

    List<CommonView> selectInventoryNum(MOrder order);
}

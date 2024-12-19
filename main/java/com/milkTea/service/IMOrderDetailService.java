package com.milkTea.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.milkTea.bean.MOrderDetail;
import com.milkTea.bean.view.MOrderDetailView;

import java.util.List;

public interface IMOrderDetailService extends IService<MOrderDetail> {

    List<MOrderDetailView> selectOrderDetailListView(MOrderDetail orderDetail);
}

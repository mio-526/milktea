package com.milkTea.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.milkTea.bean.MOrderDetail;
import com.milkTea.bean.view.MOrderDetailView;

import java.util.List;

public interface MOrderDetailMapper extends BaseMapper<MOrderDetail> {

    List<MOrderDetailView> selectOrderDetailListView(MOrderDetail orderDetail);
}

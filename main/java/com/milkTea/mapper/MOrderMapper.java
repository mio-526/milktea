package com.milkTea.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.milkTea.bean.MOrder;
import com.milkTea.bean.view.CommonView;
import com.milkTea.bean.view.MOrderView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MOrderMapper extends BaseMapper<MOrder> {

    List<MOrderView> selectOrderListView(MOrder order);

    MOrderView selectOrderViewByOrderNo(@Param("orderNo") String orderNo);

    List<MOrderView> selectOrderList(MOrder order);

    CommonView selectOrderNum(MOrder order);

    List<CommonView> selectInventoryNum(MOrder order);
}

package com.milkTea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milkTea.bean.MOrder;
import com.milkTea.bean.MUser;
import com.milkTea.bean.view.CommonView;
import com.milkTea.bean.view.MOrderView;
import com.milkTea.mapper.MOrderMapper;
import com.milkTea.service.IMOrderService;
import com.milkTea.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MOrderServiceImpl extends ServiceImpl<MOrderMapper, MOrder> implements IMOrderService {
    @Override
    public List<MOrderView> selectOrderListView(MOrder order) {
        MUser user = CommonUtil.getUser();
        order.setUserId(user.getId());
        return baseMapper.selectOrderListView(order);
    }

    @Override
    public MOrderView selectOrderViewByOrderNo(String orderNo) {
        return baseMapper.selectOrderViewByOrderNo(orderNo);
    }

    @Override
    public List<MOrderView> selectOrderList(MOrder order) {
        return baseMapper.selectOrderList(order);
    }

    @Override
    public CommonView selectOrderNum(MOrder order){
        return baseMapper.selectOrderNum(order);
    }

    public List<CommonView> selectInventoryNum(MOrder order){
        return baseMapper.selectInventoryNum(order);
    }
}

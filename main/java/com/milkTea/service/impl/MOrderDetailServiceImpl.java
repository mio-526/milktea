package com.milkTea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milkTea.bean.MOrderDetail;
import com.milkTea.bean.view.MOrderDetailView;
import com.milkTea.mapper.MOrderDetailMapper;
import com.milkTea.service.IMOrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MOrderDetailServiceImpl extends ServiceImpl<MOrderDetailMapper, MOrderDetail> implements IMOrderDetailService {

    @Override
    public List<MOrderDetailView> selectOrderDetailListView(MOrderDetail orderDetail) {
        return baseMapper.selectOrderDetailListView(orderDetail);
    }
}

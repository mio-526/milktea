package com.milkTea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milkTea.bean.MProduct;
import com.milkTea.bean.view.MProductView;
import com.milkTea.mapper.MProductMapper;
import com.milkTea.service.IMProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MProductServiceImpl extends ServiceImpl<MProductMapper, MProduct> implements IMProductService {

    @Override
    public List<MProductView> selectMProductList(MProduct product) {
        return baseMapper.selectMProductList(product);
    }

    @Override
    public MProductView selectProductInventoryByProductId(Integer id) {
        return baseMapper.selectProductInventoryByProductId(id);
    }
}

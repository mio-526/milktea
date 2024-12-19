package com.milkTea.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.milkTea.bean.MProduct;
import com.milkTea.bean.view.MProductView;

import java.util.List;

public interface MProductMapper extends BaseMapper<MProduct> {

    List<MProductView> selectMProductList(MProduct product);

    MProductView selectProductInventoryByProductId(Integer id);
}

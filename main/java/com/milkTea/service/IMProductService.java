package com.milkTea.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.milkTea.bean.MProduct;
import com.milkTea.bean.view.MProductView;

import java.util.List;

public interface IMProductService extends IService<MProduct> {
    List<MProductView> selectMProductList(MProduct product);

    MProductView selectProductInventoryByProductId(Integer id);
}

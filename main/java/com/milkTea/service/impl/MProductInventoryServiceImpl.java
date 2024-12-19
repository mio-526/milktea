package com.milkTea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milkTea.bean.MProductInventory;
import com.milkTea.mapper.MProductInventoryMapper;
import com.milkTea.service.IMProductInventoryService;
import org.springframework.stereotype.Service;

@Service
public class MProductInventoryServiceImpl extends ServiceImpl<MProductInventoryMapper, MProductInventory> implements IMProductInventoryService {
}

package com.milkTea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milkTea.bean.MInventory;
import com.milkTea.mapper.MInventoryMapper;
import com.milkTea.service.IMInventoryService;
import org.springframework.stereotype.Service;

@Service
public class MInventoryServiceImpl extends ServiceImpl<MInventoryMapper, MInventory> implements IMInventoryService {
}

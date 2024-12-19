package com.milkTea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milkTea.bean.MCategory;
import com.milkTea.mapper.MCategoryMapper;
import com.milkTea.service.IMCategoryService;
import org.springframework.stereotype.Service;

@Service
public class MCategoryServiceImpl extends ServiceImpl<MCategoryMapper, MCategory> implements IMCategoryService {
}

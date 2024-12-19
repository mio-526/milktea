package com.milkTea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milkTea.bean.MEmployee;
import com.milkTea.mapper.MEmployeeMapper;
import com.milkTea.service.IMEmployeeService;
import org.springframework.stereotype.Service;

@Service
public class MEmployeeServiceImpl extends ServiceImpl<MEmployeeMapper, MEmployee> implements IMEmployeeService {
}

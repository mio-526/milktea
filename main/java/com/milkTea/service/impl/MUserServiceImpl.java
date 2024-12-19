package com.milkTea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milkTea.bean.MUser;
import com.milkTea.mapper.MUserMapper;
import com.milkTea.service.IMUserService;
import org.springframework.stereotype.Service;

@Service
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements IMUserService {
}

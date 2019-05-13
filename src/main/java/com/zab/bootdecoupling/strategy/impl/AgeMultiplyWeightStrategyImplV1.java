package com.zab.bootdecoupling.strategy.impl;

import com.zab.bootdecoupling.annotation.Pay;
import com.zab.bootdecoupling.mapper.UserMapper;
import com.zab.bootdecoupling.model.User;
import com.zab.bootdecoupling.strategy.AgeMultiplyWeightStrategy;
import com.zab.bootdecoupling.utils.BeanUtils;

import javax.annotation.Resource;

@Pay(id = 2)
public class AgeMultiplyWeightStrategyImplV1 extends BeanUtils implements AgeMultiplyWeightStrategy {

    @Resource
    private UserMapper userMapper;

    @Override
    public Double getAgeMultiplyWeight(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return Double.parseDouble(user.getId().toString());
    }
}

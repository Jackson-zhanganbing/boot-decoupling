package com.zab.bootdecoupling.service.impl;

import com.zab.bootdecoupling.factory.StrategyFactory;
import com.zab.bootdecoupling.mapper.UserMapper;
import com.zab.bootdecoupling.model.User;
import com.zab.bootdecoupling.service.IUserService;
import com.zab.bootdecoupling.strategy.AgeMultiplyWeightStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public Double getAgeMultiplyWeight(Integer id) {
        AgeMultiplyWeightStrategy ageMultiplyWeightStrategy = null;
        try{
            ageMultiplyWeightStrategy = StrategyFactory.getStrategyFactory().createStrategy(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ageMultiplyWeightStrategy.getAgeMultiplyWeight(id);
    }
}

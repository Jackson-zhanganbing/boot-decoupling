package com.zab.bootdecoupling.service;

import com.zab.bootdecoupling.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();

    Double getAgeMultiplyWeight(Integer id);
}

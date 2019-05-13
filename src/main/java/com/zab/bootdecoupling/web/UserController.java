package com.zab.bootdecoupling.web;

import com.zab.bootdecoupling.common.Msg;
import com.zab.bootdecoupling.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getAll")
    public Msg test(){
        return new Msg("0","","",userService.getAll());
    }

    @GetMapping("/getAgeMultiplyWeight")
    public Msg test(Integer id){
        return new Msg("0","","",userService.getAgeMultiplyWeight(id));
    }
}

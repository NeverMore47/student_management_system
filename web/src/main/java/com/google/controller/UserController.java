package com.google.controller;

import com.google.base.entity.Result;
import com.google.entity.dto.UserDTO;
import com.google.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public Result login() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("admin");
        userDTO.setUserPwd("123456");

        Result result = userService.login(userDTO);

        return result;
    }
}

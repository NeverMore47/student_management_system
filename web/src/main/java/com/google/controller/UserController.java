package com.google.controller;

import com.google.base.entity.Result;
import com.google.entity.dto.UserDTO;
import com.google.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Result login() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("admin");
        userDTO.setUserPwd("123456");

        Result result = userService.login(userDTO);

        return result;
    }
}

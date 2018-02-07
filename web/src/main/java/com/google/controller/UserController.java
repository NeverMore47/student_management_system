package com.google.controller;

import com.google.base.entity.Result;
import com.google.entity.dto.UserDTO;
import com.google.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

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
    public Result login(HttpServletRequest request) {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        Result result = new Result();
        if (!Objects.isNull(userName) && !Objects.isNull(password)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName("admin");
            userDTO.setUserPwd("123456");

            result = userService.login(userDTO);
        } else {
            result.setSuccessAndMessage(false, "参数错误");
        }

        return result;
    }
}

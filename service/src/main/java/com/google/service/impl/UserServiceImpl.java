package com.google.service.impl;

import com.google.base.entity.Result;
import com.google.dao.UserMapper;
import com.google.entity.dto.UserDTO;
import com.google.entity.vo.UserVO;
import com.google.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Result login(UserDTO userDTO) {
        Result result = new Result();

        UserVO userVO = userMapper.findUserByUserName(userDTO.getUserName());

        if (Objects.isNull(userVO)) {
            result.setSuccessAndMessage(false, "用户不存在");
        } else {
            if (!Objects.equals(userDTO.getUserPwd(), userVO.getUserPwd())) {
                result.setSuccessAndMessage(false, "密码错误");
            } else {
                result.setSuccess(true);
                result.addAttribute("userInfo", userVO);
            }
        }

        return result;
    }
}

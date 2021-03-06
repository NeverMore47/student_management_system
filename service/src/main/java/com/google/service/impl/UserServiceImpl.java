package com.google.service.impl;

import com.google.base.entity.Result;
import com.google.dao.UserExtendInfoMapper;
import com.google.dao.UserMapper;
import com.google.entity.dto.UserDTO;
import com.google.entity.dto.UserExtendInfoDTO;
import com.google.entity.vo.UserVO;
import com.google.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserExtendInfoMapper userExtendInfoMapper;

    @Override
    public Result login(UserDTO userDTO) {
        Result result = new Result();

        UserVO userVO = userMapper.findUserByUserName(userDTO.getUserName());

        if (Objects.isNull(userVO)) {
            result.setSuccessAndMessage(false, "用户不存在");
        } else if (Objects.equals(userVO.getIsDelete(), 0)) {
            result.setSuccessAndMessage(false, "账号被冻结，请联系管理员");
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

    @Transactional("transactionManager_student")
    @Override
    public Result createUser(UserDTO userDTO) {
        Result result = new Result();

        UserVO userVO = userMapper.findUserByUserName(userDTO.getUserName());
        if (Objects.isNull(userVO)) {
            userMapper.saveUser(userDTO);
            if (userDTO.getId() > 0) {
                // 将userId存入userExtendInfo表中
                UserExtendInfoDTO userExtendInfoDTO = new UserExtendInfoDTO();
                userExtendInfoDTO.setUserId(userDTO.getId());

                userExtendInfoMapper.saveUserExtendInfo(userExtendInfoDTO);
                result.setSuccess(true);
            } else {
                result.setSuccessAndMessage(false, "新增用户失败");
            }
        } else {
            result.setSuccessAndMessage(false, "用户名已存在");
        }
        return result;
    }

    @Transactional("transactionManager_student")
    @Override
    public Result updateUser(UserDTO userDTO) {
        Result result = new Result();

        UserVO userVO = userMapper.findUserByUserName(userDTO.getUserName());
        if (Objects.isNull(userVO) || Objects.equals(userVO.getUserName(), userDTO.getUserName())) {
            int flag = userMapper.updateUser(userDTO);
            if (flag == 1) {
                result.setSuccess(true);
            } else {
                result.setSuccessAndMessage(false, "更新用户失败");
            }
        } else {
            result.setSuccessAndMessage(false, "用户名已存在");
        }
        return result;
    }

    @Transactional("transactionManager_student")
    @Override
    public boolean deleteUser(UserDTO userDTO) {
        return userMapper.deleteUser(userDTO) == 1;
    }

    @Override
    public int countUserVOsByUserDto(UserDTO userDTO) {
        return userMapper.countUserVOsByUserDto(userDTO);
    }

    @Override
    public List<UserVO> findUserVOsByUserDto(UserDTO userDTO) {
        return userMapper.findUserVOsByUserDto(userDTO);
    }

    @Override
    public UserVO findUserById(long userId) {
        return userMapper.findUserById(userId);
    }
}

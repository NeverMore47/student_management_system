package com.google.service;

import com.google.base.entity.Result;
import com.google.entity.dto.UserDTO;
import com.google.entity.vo.UserVO;

import java.util.List;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public interface UserService {

    Result login(UserDTO userDTO);

    Result createUser(UserDTO userDTO);

    Result updateUser(UserDTO userDTO);

    boolean deleteUser(UserDTO userDTO);

    int countUserVOsByUserDto(UserDTO userDTO);

    List<UserVO> findUserVOsByUserDto(UserDTO userDTO);

    UserVO findUserById(long userId);
}

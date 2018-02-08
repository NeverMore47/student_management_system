package com.google.dao;

import com.google.entity.dto.UserDTO;
import com.google.entity.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public interface UserMapper {

    int countUserVOsByUserDto(UserDTO userDTO);

    List<UserVO> findUserVOsByUserDto(UserDTO userDTO);

    UserVO findUserByUserName(@Param("userName") String userName);

    void saveUser(@Param("user") UserDTO userDTO);

    int updateUser(@Param("user") UserDTO userDTO);

    int deleteUser(@Param("user") UserDTO userDTO);
}

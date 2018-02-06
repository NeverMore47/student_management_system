package com.google.service;

import com.google.base.entity.Result;
import com.google.entity.dto.UserDTO;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public interface UserService {

    Result login(UserDTO userDTO);
}

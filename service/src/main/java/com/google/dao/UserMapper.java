package com.google.dao;

import com.google.entity.vo.UserVO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public interface UserMapper {

    UserVO findUserByUserName(@Param("userName") String userName);
}

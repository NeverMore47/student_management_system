package com.google.dao;

import com.google.entity.dto.UserExtendInfoDTO;
import com.google.entity.vo.UserExtendInfoVO;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
public interface UserExtendInfoMapper {

    UserExtendInfoVO findUserExtendInfoByUserId(long userId);

    void saveUserExtendInfo(UserExtendInfoDTO extendInfoDTO);

    int updateUserExtendInfo(UserExtendInfoDTO extendInfoDTO);

}

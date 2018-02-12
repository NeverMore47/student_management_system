package com.google.service;

import com.google.entity.dto.UserExtendInfoDTO;
import com.google.entity.vo.UserExtendInfoVO;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
public interface UserExtendInfoService {

    UserExtendInfoVO findUserExtendInfoByUserId(long userId);

    boolean saveUserExtendInfo(UserExtendInfoDTO infoDTO);

    boolean updateUserExtendInfo(UserExtendInfoDTO infoDTO);
}

package com.google.service.impl;

import com.google.dao.UserExtendInfoMapper;
import com.google.entity.dto.UserExtendInfoDTO;
import com.google.entity.vo.UserExtendInfoVO;
import com.google.service.UserExtendInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
@Service("userExtendInfoService")
public class UserExtendInfoServiceImpl implements UserExtendInfoService {

    @Autowired
    private UserExtendInfoMapper infoMapper;

    @Override
    public UserExtendInfoVO findUserExtendInfoByUserId(long userId) {
        return infoMapper.findUserExtendInfoByUserId(userId);
    }

    @Transactional("transactionManager_student")
    @Override
    public boolean saveUserExtendInfo(UserExtendInfoDTO infoDTO) {
        infoMapper.saveUserExtendInfo(infoDTO);
        if (infoDTO.getId() > 0) {
            return true;
        }
        return false;
    }

    @Transactional("transactionManager_student")
    @Override
    public boolean updateUserExtendInfo(UserExtendInfoDTO infoDTO) {
        int flag = infoMapper.updateUserExtendInfo(infoDTO);
        if (flag == 1) {
            return true;
        }
        return false;
    }
}

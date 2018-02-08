package com.google.controller;

import com.google.base.entity.Result;
import com.google.entity.dto.UserExtendInfoDTO;
import com.google.entity.vo.UserExtendInfoVO;
import com.google.service.UserExtendInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;


/**
 * Created by wanjiahuan on 2018/2/8.
 * Description
 */
@Controller
@RequestMapping("/userExtendInfo")
public class UserExtendInfoController {

    @Autowired
    private UserExtendInfoService userExtendInfoService;

    @RequestMapping("/findUserExtendInfo")
    @ResponseBody
    public Result findUserExtendInfoByUserId(long userId) {
        Result result = new Result();

        if (userId != 0) {
            UserExtendInfoVO infoVO = userExtendInfoService.findUserExtendInfoByUserId(userId);
            result.setSuccess(true);
            result.addAttribute("userExtendInfoVO", infoVO);
        } else {
            result.setSuccessAndMessage(false, "参数错误");
        }
        return result;
    }

    @RequestMapping("/saveUserExtendInfo")
    @ResponseBody
    public Result saveUserExtendInfo(UserExtendInfoDTO infoDTO, int type) {

        Result result = new Result();

        if (infoDTO.getUserId() != 0  && Objects.isNull(infoDTO.getId()) && type == 1) {
            result.setSuccess(userExtendInfoService.saveUserExtendInfo(infoDTO));
        } else if (infoDTO.getUserId() == 0 && !Objects.isNull(infoDTO.getId()) && type == 2) {
            result.setSuccess(userExtendInfoService.updateUserExtendInfo(infoDTO));
        } else {
            result.setSuccessAndMessage(false, "参数错误");
        }
        return result;
    }

}

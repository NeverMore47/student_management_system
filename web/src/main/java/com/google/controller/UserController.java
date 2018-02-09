package com.google.controller;

import com.google.base.entity.Result;
import com.google.entity.dto.UserDTO;
import com.google.entity.vo.UserVO;
import com.google.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Result login(UserDTO userDTO) {

        String userName = userDTO.getUserName();
        String password = userDTO.getUserPwd();

        Result result = new Result();
        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
            result = userService.login(userDTO);
        } else {
            result.setSuccessAndMessage(false, "参数错误");
        }

        return result;
    }

    @RequestMapping("/getUserBaseInfo")
    @ResponseBody
    public Result getUserBaseInfo(@RequestParam(value = "userId", defaultValue = "0") long userId) {
        Result result = new Result();

        if (userId != 0) {
            UserVO userVO = userService.findUserById(userId);

            if (Objects.isNull(userVO)) {
                result.setSuccessAndMessage(false, "找不到信息");
            } else {
                result.setSuccess(true);
                result.addAttribute("userInfo", userVO);
            }

        } else {
            result.setSuccessAndMessage(false, "参数错误");
        }

        return result;
    }

    @RequestMapping("/createUser")
    @ResponseBody
    public Result createUser(UserDTO userDTO) {

        String userName = userDTO.getUserName();
        String password = userDTO.getUserPwd();
        Integer userRoleId = userDTO.getUserRoleId();

        Result result = new Result(false);

        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password) && !Objects.isNull(userRoleId)) {
            return userService.createUser(userDTO);
        } else {
            result.setMessage("参数错误");
        }

        return result;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Result updateUser(UserDTO userDTO) {

        String userName = userDTO.getUserName();
        String password = userDTO.getUserPwd();
        Integer userRoleId = userDTO.getUserRoleId();
        long userId = userDTO.getId();

        Result result = new Result(false);

        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)
                && !Objects.isNull(userRoleId) && userId != 0) {
            return userService.updateUser(userDTO);
        } else {
            result.setMessage("参数错误");
        }

        return result;
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Result deleteUser(UserDTO userDTO) {
        Integer isDelete = userDTO.getIsDelete();
        long userId = userDTO.getId();

        Result result = new Result(false);
        if (!Objects.isNull(isDelete) && userId != 0) {
            result.setSuccess(userService.deleteUser(userDTO));
        } else {
            result.setMessage("参数错误");
        }
        return result;
    }

    @RequestMapping("/findUserList")
    @ResponseBody
    public Result findUserVOsByUserDto(UserDTO userDTO) {

        int count = userService.countUserVOsByUserDto(userDTO);
        Result result = new Result();
        result.addAttribute("count", count);

        if (count != 0) {
            List<UserVO> userVOList = userService.findUserVOsByUserDto(userDTO);
            result.addAttribute("userList", userVOList);
        }

        return result;
    }
}

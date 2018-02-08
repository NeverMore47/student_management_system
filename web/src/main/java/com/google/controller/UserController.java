package com.google.controller;

import com.google.base.entity.Result;
import com.google.entity.dto.UserDTO;
import com.google.entity.vo.UserVO;
import com.google.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/login")
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

    @RequestMapping("/createUser")
    @ResponseBody
    public Result createUser(UserDTO userDTO) {

        String userName = userDTO.getUserName();
        String password = userDTO.getUserPwd();
        Integer userRoleId = userDTO.getUserRoleId();

        Result result = new Result(false);

        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password) && !Objects.isNull(userRoleId)) {
            userDTO.setUserName(userName);
            userDTO.setUserPwd(password);
            userDTO.setUserRoleId(userRoleId);

            if (userService.createUser(userDTO)) {
                result.setSuccess(true);
            }
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
        Integer isDelete = userDTO.getIsDelete();
        long userId = userDTO.getId();

        Result result = new Result(false);

        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
            userDTO.setUserName(userName);
            userDTO.setUserPwd(password);
            userDTO.setUserRoleId(userRoleId);
            userDTO.setIsDelete(isDelete);
            userDTO.setId(userId);

            result.setSuccess(userService.updateUser(userDTO));
        }

        return result;
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public boolean deleteUser(HttpServletRequest request) {
        String isDelete = request.getParameter("isDelete");
        String userId = request.getParameter("userId");
        
        if (!StringUtils.isEmpty(isDelete) && !StringUtils.isEmpty(userId)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setIsDelete(Integer.valueOf(isDelete));
            userDTO.setId(Long.valueOf(userId));
            
            return userService.deleteUser(userDTO);
        }
        return false;
    }

    @RequestMapping("/findUserList")
    @ResponseBody
    public List<UserVO> findUserVOsByUserDto(UserDTO userDTO) {
        userDTO.setStart(1);
        userDTO.setUserName("admin");
        int count = userService.countUserVOsByUserDto(userDTO);
        if (count == 0) {
            return null;
        }

        return userService.findUserVOsByUserDto(userDTO);
    }
}

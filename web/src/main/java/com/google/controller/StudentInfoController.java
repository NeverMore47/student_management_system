package com.google.controller;

import com.google.base.entity.Result;
import com.google.entity.dto.StudentInfoDTO;
import com.google.entity.vo.StudentInfoVO;
import com.google.service.StudentInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wanjiahuan on 2018/2/9.
 * Description
 */
@Controller
@RequestMapping("/studentInfo")
public class StudentInfoController {

    @Autowired
    private StudentInfoService studentInfoService;

    @RequestMapping("/findStudentInfoList")
    @ResponseBody
    public Result findStudentInfoListByDto(StudentInfoDTO studentInfoDTO) {
        Result result = new Result();

        int count = studentInfoService.countStudentInfoListByDto(studentInfoDTO);
        result.addAttribute("count", count);

        if (count != 0) {
            List<StudentInfoVO> infoVOS = studentInfoService.findStudentInfoListByDto(studentInfoDTO);
            result.addAttribute("infoList", infoVOS);
        }

        return result;
    }

    @RequestMapping("/saveStudentInfo")
    @ResponseBody
    public Result saveStudentInfo(StudentInfoDTO infoDTO, @RequestParam(value = "type", defaultValue = "0") int type) {
        Result result = new Result();

        String studentNo = infoDTO.getStudentNo();
        String studentRealName = infoDTO.getStudentRealName();

        if (!StringUtils.isEmpty(studentNo) && !StringUtils.isEmpty(studentRealName) && type != 0) {
            if (type == 1) {
                result.setSuccess(studentInfoService.saveStudentInfo(infoDTO));
            } else if (type == 2) {
                result.setSuccess(studentInfoService.updateStudentInfo(infoDTO));
            } else {
                result.setSuccessAndMessage(false, "参数错误");
            }

        } else {
            result.setSuccessAndMessage(false, "参数错误");
        }

        return result;
    }

    @RequestMapping("/deleteStudentInfo")
    @ResponseBody
    public Result deleteStudentInfo(@RequestParam(value = "id", defaultValue = "0") long id) {
        Result result = new Result();

        if (id != 0) {
            result.setSuccess(studentInfoService.deleteStudentInfoById(id));
        } else {
            result.setSuccessAndMessage(false, "参数错误");
        }

        return result;
    }
}

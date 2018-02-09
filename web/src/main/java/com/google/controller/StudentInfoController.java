package com.google.controller;

import com.google.base.entity.Result;
import com.google.entity.dto.StudentInfoDTO;
import com.google.entity.vo.StudentInfoVO;
import com.google.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

}

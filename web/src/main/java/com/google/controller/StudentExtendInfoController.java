package com.google.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.entity.dto.StudentExtendInfoDTO;
import com.google.entity.vo.StudentExtendInfoVO;
import com.google.service.StudentExtendInfoService;

@Controller
@RequestMapping("/studentExtendInfo")
public class StudentExtendInfoController {

	@Autowired
	private StudentExtendInfoService studentExtendInfoService;
	
	@RequestMapping(value = "/findStudentExtendInfoList")
    @ResponseBody
	public List<StudentExtendInfoVO> findStudentExtendInfoList(StudentExtendInfoDTO dto) {
		return studentExtendInfoService.findStudentExtendInfoList(dto);
	}
	
	
}

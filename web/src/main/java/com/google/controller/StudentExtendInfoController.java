package com.google.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.base.entity.Result;
import com.google.entity.dto.StudentExtendInfoDTO;
import com.google.entity.vo.StudentExtendInfoVO;
import com.google.entity.vo.StudentInfoVO;
import com.google.service.StudentExtendInfoService;

@Controller
@RequestMapping("/studentExtendInfo")
public class StudentExtendInfoController {

	@Autowired
	private StudentExtendInfoService studentExtendInfoService;
	
	@RequestMapping(value = "/findStudentExtendInfoList")
    @ResponseBody
	public Result findStudentExtendInfoList(StudentExtendInfoDTO dto) {
		Result result = new Result();
		
		int count = studentExtendInfoService.countStudentExtendInfo(dto);
		result.addAttribute("count", count);
		
		if (count != 0) {
			List<StudentExtendInfoVO> list = studentExtendInfoService.findStudentExtendInfoList(dto);
			result.addAttribute("infoList", list);
		}
		
		return result;
	}
	
	@RequestMapping("/saveStudentExtendInfo")
	@ResponseBody
	public Result saveStudentExtendInfo(StudentExtendInfoDTO dto) {
		Result result = new Result();
		studentExtendInfoService.saveStudentExtendInfo(dto);
		result.setSuccessAndMessage(true, "新增成功");
		return result;
	}
	
	@RequestMapping("/updateStudentExtendInfo")
	@ResponseBody
	public Result updateStudentExtendInfo(StudentExtendInfoDTO dto) {
		Result result = new Result();
		studentExtendInfoService.updateStudentExtendInfo(dto);
		result.setSuccessAndMessage(true, "更新成功");
		return result;
	}
	
	@RequestMapping("/findStudentAllInfoById")
	@ResponseBody
	public Result findStudentAllInfoById(Long id) {
		Result result = new Result();
		StudentInfoVO studentInfoVO = studentExtendInfoService.findStudentAllInfoById(id);
		result.addAttribute("studentExtendInfoVO", studentInfoVO);
		return result; 
	}
}

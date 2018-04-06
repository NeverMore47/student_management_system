package com.google.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.dao.StudentExtendInfoMapper;
import com.google.entity.dto.StudentExtendInfoDTO;
import com.google.entity.vo.StudentExtendInfoVO;
import com.google.service.StudentExtendInfoService;

public class StudentExtendInfoServiceImpl implements StudentExtendInfoService {

	@Autowired
	private StudentExtendInfoMapper studentExtendInfoMapper;
	
	@Override
	public List<StudentExtendInfoVO> findStudentExtendInfoList(StudentExtendInfoDTO dto) {
		return studentExtendInfoMapper.findStudentExtendInfoList();
	}

}

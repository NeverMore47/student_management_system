package com.google.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.dao.StudentExtendInfoMapper;
import com.google.entity.dto.StudentExtendInfoDTO;
import com.google.entity.vo.StudentExtendInfoVO;
import com.google.entity.vo.StudentInfoVO;
import com.google.service.StudentExtendInfoService;

@Service("studentExtendInfoService")
public class StudentExtendInfoServiceImpl implements StudentExtendInfoService {

	@Autowired
	private StudentExtendInfoMapper studentExtendInfoMapper;
	
	@Override
	public int countStudentExtendInfo(StudentExtendInfoDTO dto) {
		return studentExtendInfoMapper.countStudentExtendInfo(dto);
	}
	
	@Override
	public List<StudentExtendInfoVO> findStudentExtendInfoList(StudentExtendInfoDTO dto) {
		return studentExtendInfoMapper.findStudentExtendInfoList(dto);
	}

	@Override
	public void saveStudentExtendInfo(StudentExtendInfoDTO dto) {
		studentExtendInfoMapper.saveStudentExtendInfo(dto);
	}

	@Override
	public void updateStudentExtendInfo(StudentExtendInfoDTO dto) {
		studentExtendInfoMapper.updateStudentExtendInfo(dto);
	}

	@Override
	public StudentInfoVO findStudentAllInfoById(long id) {
		return studentExtendInfoMapper.findStudentAllInfoById(id);
	}

}

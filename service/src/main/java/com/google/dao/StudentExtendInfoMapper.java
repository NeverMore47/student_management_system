package com.google.dao;

import java.util.List;

import com.google.entity.dto.StudentExtendInfoDTO;
import com.google.entity.vo.StudentExtendInfoVO;
import com.google.entity.vo.StudentInfoVO;

public interface StudentExtendInfoMapper {
	
	int countStudentExtendInfo(StudentExtendInfoDTO dto);
	
	List<StudentExtendInfoVO> findStudentExtendInfoList(StudentExtendInfoDTO dto);
	
	void saveStudentExtendInfo(StudentExtendInfoDTO dto);
	
	void updateStudentExtendInfo(StudentExtendInfoDTO dto);
	
	StudentInfoVO findStudentAllInfoById(long id);
}

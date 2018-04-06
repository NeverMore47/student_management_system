package com.google.service;

import java.util.List;

import com.google.entity.dto.StudentExtendInfoDTO;
import com.google.entity.vo.StudentExtendInfoVO;

public interface StudentExtendInfoService {

	List<StudentExtendInfoVO> findStudentExtendInfoList(StudentExtendInfoDTO dto);
}

package com.google.service;

import com.google.entity.dto.StudentInfoDTO;
import com.google.entity.vo.StudentInfoVO;

import java.util.List;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public interface StudentInfoService {

    int countStudentInfoListByDto(StudentInfoDTO infoDTO);

    List<StudentInfoVO> findStudentInfoListByDto(StudentInfoDTO infoDTO);
}
